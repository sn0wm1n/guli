package com.snoman.mpdemo1010;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.snoman.mpdemo1010.mapper")//找到mapper因为mapper接口没有实现
public class Mpdemo1010Application {

    public static void main(String[] args) {
        SpringApplication.run(Mpdemo1010Application.class, args);
    }

}
