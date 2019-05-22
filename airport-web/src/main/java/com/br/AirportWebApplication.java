package com.br;

import com.br.entity.map.Car;
import com.br.entity.map.CarInfo;
import com.br.service.service.task.TrafficTaskStateService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
        CarInfo carInfo = new CarInfo();
        carInfo.setCar(car);
        String[] str = trafficTaskStateService.getTaskState(carInfo);
        System.out.println(str[0] + "---" + str[1] + "---" + str[2] + "---" + str[3]);
    }
}

