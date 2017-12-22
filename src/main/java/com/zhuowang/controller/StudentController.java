package com.zhuowang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuowang.entity.Student;
import com.zhuowang.entity.User;
import com.zhuowang.mapper.StudentMapper;
import com.zhuowang.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@RequestMapping("/create")
	public void createStudent(@RequestParam("name") String name,@RequestParam("age") int age){
		studentMapper.insert(name, age);
		logger.info("从数据库创建student");
	}
	
	@RequestMapping("/find")
	public Student findStudent(@RequestParam("name") String name){
		return studentMapper.findByName(name);
	}
	
	@RequestMapping("/page")
	public PageInfo page(@RequestParam int pageNum, @RequestParam int pageSize){
		logger.info("从数据库读取student列表");
		
		PageInfo page = new PageInfo(studentMapper.list());
		return page;
	}
	
	@RequestMapping("/part")
    public List<Student> part(@RequestParam int pageNum, @RequestParam int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return studentMapper.list();
    }
	
	@RequestMapping("/listAll")
	public List<Student> listAll(){
		logger.info("从数据库读取student列表");
		return studentMapper.list();
	}
	
	@RequestMapping("/listUser")
	public List<User> listUser(){
		return studentService.listUser();
	}
	
	@RequestMapping("/list")
	public List<Student> getList(){
		logger.info("从数据库读取student列表");
		return studentService.getList();
	}
}
