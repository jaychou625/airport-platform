package com.br;

import com.pl.firstSocket.nettyWork.TCPServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.br.mapper")
public class AirportWebApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AirportWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TCPServer tcpServer = new TCPServer();
        tcpServer.run();
    }
}

