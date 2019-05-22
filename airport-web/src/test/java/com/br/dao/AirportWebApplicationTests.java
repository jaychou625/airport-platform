package com.br.dao;

import com.br.service.service.task.TrafficTaskStateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportWebApplicationTests {
    @Autowired
    TrafficTaskStateService trafficTaskStateService;

    @Test
    public void contextLoads() {


    }

}

