package com.zhuowang.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuowang.entity.Student;
import com.zhuowang.entity.User;
import com.zhuowang.mapper.StudentMapper;
import com.zhuowang.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value="swagger测试")
public class ApiController {

	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@ApiOperation(value="获取json列表",notes="测试获取json列表")
	@GetMapping("/getListJson")
	public String getListJson(){
		ObjectMapper mapper= new ObjectMapper();
		User u1 = new User(1,"lebron",1);
		User u2 = new User(2,"james",2);
		
		String jsonStr = null;
		String jsonListStr = null;
		try {
			jsonStr = mapper.writeValueAsString(u1);
			System.out.println("jsonStr:"+jsonStr);
			
			List<User> list = Arrays.asList(u1,u2);
			jsonListStr = mapper.writeValueAsString(list);
			System.out.println("jsonListStr:"+jsonListStr);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonListStr;
	}
	
	@ApiOperation(value="解析json列表",notes="测试解析json列表")
	@PostMapping("/postListJson")
	@ResponseBody
	public void postListJson(@RequestBody List<User> user){
		ObjectMapper mapper= new ObjectMapper();
		
//		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);
//		List<User> list1 = mapper.readValue(user, listType);
		System.out.println("jsonarray 转list,size:"+user.size());
		for(User u : user){
			System.out.println("++++++++++++,user##id:"+u.getId()+",name:"+u.getName()+",age:"+u.getAge());
		}
	}
	
	public static void main(String[] args) {
		new ApiController().test();
	}
	
	@RequestMapping(value="/test")
	public void test(){
		ObjectMapper mapper= new ObjectMapper();
		User u1 = new User(1,"lebron",1);
		User u2 = new User(2,"james",2);
		
		String jsonStr = null;
		String jsonArrayStr = null;
		String jsonListStr = null;
		String mapStr = null;
		String mapListStr = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			jsonStr = mapper.writeValueAsString(u1);
			System.out.println("jsonStr:"+jsonStr);
			
			User[] array = new User[]{u1,u2};
			jsonArrayStr = mapper.writeValueAsString(array);
			System.out.println("jsonArrayStr:"+jsonArrayStr);
			
			List<User> list = Arrays.asList(u1,u2);
			jsonListStr = mapper.writeValueAsString(list);
			System.out.println("jsonListStr:"+jsonListStr);
			
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("userList", jsonListStr);
			mapListStr = mapper.writeValueAsString(map1);
			System.out.println("---------------------mapListStr:"+mapListStr);
			
			map.put("key1", u1);
			map.put("key2", u2);
			
			mapStr = mapper.writeValueAsString(map);
			System.out.println("mapStr:"+mapStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		try {
			User u = mapper.readValue(jsonStr, User.class);
			System.out.println("user:"+u.toString());
			//jsonArray转换成Array数组
			ArrayType arrayType = mapper.getTypeFactory().constructArrayType(User.class);
			User[] array1 = mapper.readValue(jsonArrayStr, arrayType);
			System.out.println("jsonarray 转array,length:"+array1.length);
			//jsonarray 转list
			CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);
			List<User> list1 = mapper.readValue(jsonListStr, listType);
			System.out.println("jsonarray 转list,size:"+list1.size());
			
			MapType mapType = mapper.getTypeFactory().constructMapType(Map.class,String.class,List.class);
//			Object o = mapper.readValue(mapListStr, mapType);
			
//			String expected = "[{\"name\":\"Ryan\"},{\"name\":\"Test\"},{\"name\":\"Leslie\"}]";
//			ArrayList arrayList = mapper.readValue(expected, ArrayList.class);
//			Object o = arrayList.get(0);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public void createStudent(@RequestParam("name") String name,@RequestParam("age") int age){
		studentMapper.insert(name, age);
		logger.info("从数据库创建student");
	}
	
	@RequestMapping(value="/find",method=RequestMethod.GET)
	public Student findStudent(@RequestParam("name") String name){
		return studentMapper.findByName(name);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public PageInfo page(@RequestParam int pageNum, @RequestParam int pageSize){
		logger.info("从数据库读取student列表");
		
		PageInfo page = new PageInfo(studentMapper.list());
		return page;
	}
	
	@RequestMapping(value="/part",method=RequestMethod.GET)
    public List<Student> part(@RequestParam int pageNum, @RequestParam int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return studentMapper.list();
    }
	
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public List<Student> listAll(){
		logger.info("从数据库读取student列表");
		return studentMapper.list();
	}
	
	@RequestMapping(value="/listUser",method=RequestMethod.GET)
	public List<User> listUser(){
		return studentService.listUser();
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<Student> getList(){
		logger.info("从数据库读取student列表");
		return studentService.getList();
	}
}
