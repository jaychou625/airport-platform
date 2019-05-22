package com.br;

import com.br.entity.map.Car;
import com.br.entity.map.CarInfo;
import com.br.entity.task.TaskInfo;
import com.br.entity.task.TaskStateInfo;
import com.br.service.service.task.TrafficTaskStateService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.br.mapper")
public class AirportWebApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AirportWebApplication.class, args);
    }

    @Autowired
    TrafficTaskStateService trafficTaskStateService;

    @Override
    public void run(String... args) throws Exception {
//        TCPServer tcpServer = new TCPServer();
//        tcpServer.run();
    }

    @Scheduled(fixedRate = 2000)
    public void todo(){
        Car car = new Car();
        car.setCarType("摆渡车");
        car.setCarNo("摆渡车No");
        CarInfo carInfo = new CarInfo();
        carInfo.setCar(car);
        TaskStateInfo taskState = trafficTaskStateService.getTaskState(carInfo);
        if(taskState.getState() != -1){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String endTime = taskState.getState() == 2 ?  sdf.format(taskState.getEndTime()) : "无结束时间";
            System.out.println(taskState.getId() + "---" + taskState.getCarType() + "---" + taskState.getFltNo() + "---" + taskState.getCarNo() + "---" + taskState.getDriverName() + "---" + taskState.getStartTime() + "---" + endTime + "---" + taskState.getState());
        }
    }

    @Scheduled(fixedRate = 2000)
    public void todo2(){
        Car car = new Car();
        car.setCarType("清水车");
        car.setCarNo("清水车No");
        CarInfo carInfo = new CarInfo();
        carInfo.setCar(car);
        TaskStateInfo taskState = trafficTaskStateService.getTaskState(carInfo);
        if(taskState.getState() != -1){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String endTime = taskState.getState() == 2 ?  sdf.format(taskState.getEndTime()) : "无结束时间";
            System.out.println(taskState.getId() + "---" + taskState.getCarType() + "---" + taskState.getFltNo() + "---" + taskState.getCarNo() + "---" + taskState.getDriverName() + "---" + taskState.getStartTime() + "---" + endTime + "---" + taskState.getState());
        }
    }

    @Scheduled(fixedRate = 2000)
    public void todo3(){
        Car car = new Car();
        car.setCarType("拖头车");
        car.setCarNo("拖头车No");
        CarInfo carInfo = new CarInfo();
        carInfo.setCar(car);
        TaskStateInfo taskState = trafficTaskStateService.getTaskState(carInfo);
        if(taskState.getState() != -1){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String endTime = taskState.getState() == 2 ?  sdf.format(taskState.getEndTime()) : "无结束时间";
            System.out.println(taskState.getId() + "---" + taskState.getCarType() + "---" + taskState.getFltNo() + "---" + taskState.getCarNo() + "---" + taskState.getDriverName() + "---" + taskState.getStartTime() + "---" + endTime + "---" + taskState.getState());
        }
    }

    @Scheduled(fixedRate = 2000)
    public void todo4(){
        Car car = new Car();
        car.setCarType("牵引车");
        car.setCarNo("牵引车No");
        CarInfo carInfo = new CarInfo();
        carInfo.setCar(car);
        TaskStateInfo taskState = trafficTaskStateService.getTaskState(carInfo);
        if(taskState.getState() != -1){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String endTime = taskState.getState() == 2 ?  sdf.format(taskState.getEndTime()) : "无结束时间";
            System.out.println(taskState.getId() + "---" + taskState.getCarType() + "---" + taskState.getFltNo() + "---" + taskState.getCarNo() + "---" + taskState.getDriverName() + "---" + taskState.getStartTime() + "---" + endTime + "---" + taskState.getState());
        }
    }
}

