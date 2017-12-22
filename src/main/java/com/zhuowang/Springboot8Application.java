package com.zhuowang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.zhuowang.datasource.DynamicDataSourceRegister;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class Springboot8Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot8Application.class, args);
	}
}
