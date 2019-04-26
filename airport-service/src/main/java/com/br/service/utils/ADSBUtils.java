package com.br.service.utils;

import com.br.entity.map.Plane;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ADSB 工具类
 *
 * @Author Zero
 * @Date 2019 03 02
 */
public class ADSBUtils {

    /**
     * ADSB 字符串转换为 PlaneBeanList
     *
     * @param planes 飞机信息
     * @return List<Plane>
     */
    public List<Plane> toPlaneList(String planes) {
        Long receiveTimeOfLong = System.currentTimeMillis();
        String planes_replace = planes.replaceAll(",,", ",");
        String planes_sub = planes_replace.substring(planes_replace.indexOf(",") + 1, planes_replace.lastIndexOf("End"));
        String[] aDSBInfos_split = planes_sub.split(",");
        List<String> planesStringList = new ArrayList<>();
        Integer index = 0;
        String planeString = "";
        for (Integer i = 0; i < aDSBInfos_split.length; i++) {
            index++;
            planeString += aDSBInfos_split[i] + ",";
            if (i != 0 && index == 11) {
                planesStringList.add(planeString.substring(0, planeString.length() - 1));
                planeString = "";
                index = 0;
            }
        }
        List<Plane> planeBeans = new ArrayList<>();
        for (String planeString_ : planesStringList) {
            Plane plane = new Plane();
            String[] planeInfos = planeString_.split(",");
            plane.setAircraftSeq(planeInfos[0]);
            plane.setDataSourceDept(planeInfos[1]);
            plane.setAircraftLongitude(new BigDecimal(planeInfos[2]));
            plane.setAircraftLatitude(new BigDecimal(planeInfos[3]));
            plane.setAircraftVerticalSpeed(Integer.parseInt(planeInfos[4]));
            plane.setAircraftGroundVelocity(Integer.parseInt(planeInfos[5]));
            plane.setAircraftHeight(Integer.parseInt(planeInfos[6]));
            plane.setReceiveAircraftCode(planeInfos[7]);
            if (planeInfos[8].equals("")) {
                plane.setFlightNumber(planeInfos[0]);
            } else {
                plane.setFlightNumber(planeInfos[8]);
            }
            plane.setAircraftHeading(new BigDecimal(planeInfos[9]));
            plane.setAckAircraftCode(planeInfos[10]);
            plane.setReceiveTimeOfLong(receiveTimeOfLong);
            planeBeans.add(plane);
        }
        return planeBeans;
    }
}
