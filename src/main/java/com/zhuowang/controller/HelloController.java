package com.zhuowang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/test")
public class HelloController {

	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	@ResponseBody
	@RequestMapping("/world")
	public String world(){
		return "world";
	}
}
