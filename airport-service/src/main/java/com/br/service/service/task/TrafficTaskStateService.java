package com.br.service.service.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.br.entity.map.CarInfo;
import com.br.entity.task.TaskInfo;
import com.br.entity.task.TaskObject;
import com.pl.firstSocket.utils.MapSort;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 交通任务状态服务
 */
@Service("trafficTaskStateService")
public class TrafficTaskStateService {

    /**
     * 根据车辆信息查询任务状态，0：任务开始，1：任务进行中，2：任务结束
     * @param carInfo
     * @return Map:key--"state":任务状态（0：任务开始，1:任务进行中，2：任务结束，-1任务未开始），
     * "portNo":机位，"fltNo":航班号,"taskInfo":任务信息对象
     */
    public Map<String,Object> getTaskState(CarInfo carInfo){

        Map<String,Object> state = new HashMap<>();
        //获取车辆类型
        String carType = carInfo.getCar().getCarType();
        //查询redis数据库匹配车型最新的任务记录
        Jedis jedis = new Jedis("localhost",6379);
        jedis.select(3);
        //获取最新的任务信息
        Map<String,String> map = jedis.hgetAll(carType);
        if(map == null){
            return state;
        }
        map = MapSort.sortMapByKey(map);
        Iterator<Map.Entry<String,String>> entries = map.entrySet().iterator();
        Map.Entry<String,String> entry = entries.next();
        TaskInfo taskInfo = JSON.parseObject(entry.getValue(),new TypeReference<TaskInfo>() {});
        //获取任务状态0：开始，1：任务中，2：任务结束
        if(taskInfo.getStartTime() != null && taskInfo.getEndTime() == null) {
            //判断是否为第一次接受到任务
            if(taskInfo.getCount() == null){//任务开始
                state.put("state",0);
                taskInfo.setCount(1);
                jedis.hset(taskInfo.getPrcName(),String.valueOf(taskInfo.getStartTime().getTime()),JSON.toJSONString(taskInfo));
            }else{//执行中
                state.put("state",1);
            }
        }else if(taskInfo.getStartTime() != null && taskInfo.getEndTime() != null){//任务结束
            state.put("state",2);
        }else{
            state.put("state",-1);
        }
        //获取任务对应航班号
        jedis.select(4);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = "";
        try {
            tempDate = String.valueOf(sdf.parse(sdf.format(taskInfo.getSendTime())).getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        TaskObject taskObject = JSON.parseObject(jedis.hget(String.valueOf(taskInfo.getFid()),tempDate),new TypeReference<TaskObject>() {});
        if(taskObject != null){
            state.put("portNo",taskObject.getPortNo());
            state.put("fltNo",taskObject.getFltNo());
        }else{
            state.put("portNo","");
            state.put("fltNo","");
        }

        state.put("taskInfo",taskInfo);





        jedis.close();
        return state;
    }
}
