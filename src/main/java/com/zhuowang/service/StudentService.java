package com.zhuowang.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.zhuowang.datasource.TargetDataSource;
import com.zhuowang.entity.Student;
import com.zhuowang.entity.User;
import com.zhuowang.mapper.UserMapper;

@Service
public class StudentService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private UserMapper userMapper;
	
	@TargetDataSource(name="ds1")
    public List<User> listUser(){
        return userMapper.list();
    }

	
	public List<Student> getList(){
		String sql = "select ID,NAME,SCORE_SUM,SCORE_AVG,AGE from t_student";
		return jdbcTemplate.query(sql, new RowMapper<Student>(){

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(rs.getInt("ID"));
				student.setName(rs.getString("NAME"));
				student.setSumScore(rs.getString("SCORE_SUM"));
				student.setAvgScore(rs.getString("SCORE_AVG"));
				student.setAge(rs.getInt("AGE"));
				return student;
			}
		});
	}
}
