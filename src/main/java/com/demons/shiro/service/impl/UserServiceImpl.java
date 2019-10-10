package com.demons.shiro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demons.common.PageTool.PageBean;
import com.demons.shiro.mapper.UserMapper;
import com.demons.shiro.model.User;
import com.demons.shiro.service.UserService;
import com.demons.shiro.test.MyException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Override
	public User selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public String test() {
		System.out.println("测试------->");
		return "哈哈哈";
	}

	@Override
//	 @SMSAndMailSender(smsContent = "MODEL_SUBMIT_SMS", mailContent =     
//	  "MODEL_SUPPLIER_EMAIL", subject = "MODEL_SUBJECT_EMAIL")
	public String test(String name) {
		return name;
	}

	@Override
	public List<User> selectList(Map<String, Object> map,PageBean<User> page) {
		// TODO Auto-generated method stub
		return userMapper.selectList(map,page);
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return userMapper.selectAll();
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public int updateByPrimaryKeySelective(User user) {
		// TODO Auto-generated method stub
		int num = userMapper.updateByPrimaryKeySelective(user);
		try {
			if (num>0) {
				throw new MyException("测试 事物的回滚");
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
}
