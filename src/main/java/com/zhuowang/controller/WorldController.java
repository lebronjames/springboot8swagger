package com.zhuowang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class WorldController {
	private static final Logger logger = LoggerFactory.getLogger(WorldController.class);

	@RequestMapping("/world")
	public String world(){
		logger.debug("日志输出测试 Debug");
	    logger.trace("日志输出测试 Trace");
	    logger.info("日志输出测试 Info");
	    logger.error("日志输出测试 Error");
		return "world";
	}
	
	@RequestMapping("/map")
	public Map<String,String> map(@RequestParam String name){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		return map;
	}
	
	@RequestMapping("/list")
	public List<Map<String,String>> list(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		for(int i=0;i<5;i++){
			map = new HashMap<String,String>();
			map.put("name", "Shary - "+i);
			list.add(map);
		}
		return list;
	}
}
