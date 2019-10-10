package com.demons.shiro.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demons.common.PageTool.PageBean;
import com.demons.shiro.model.User;

@Service
public interface UserService {
	User selectByPrimaryKey(String id);
	
	String test();
	
	String test(String name);
	
	List<User> selectList(Map<String, Object> map,PageBean<User> page);
	
	List<User> selectAll();
	
	int updateByPrimaryKeySelective(User user);
}
