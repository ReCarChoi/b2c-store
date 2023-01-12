package com.recarchoi.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author recarchoi
 * @since 2023/1/12 16:13
 * @description 用户服务的启动类
 */
@MapperScan(basePackages = "com.recarchoi.user.mapper")
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
