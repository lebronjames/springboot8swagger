package com.zhuowang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zhuowang.entity.Student;

@Mapper
public interface StudentMapper {

	@Select("SELECT * FROM t_student WHERE NAME = #{name}")
	Student findByName(@Param("name") String name);
	
	@Insert("INSERT INTO t_student(NAME,AGE) VALUES (#{name},#{age})")
	int insert(@Param("name") String name,@Param("age") Integer age);
	
	@Select("select * from t_student")  
	List<Student> list();
}
