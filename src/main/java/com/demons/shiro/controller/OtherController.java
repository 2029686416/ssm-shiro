package com.demons.shiro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demons.common.PageTool.PageBean;
import com.demons.shiro.mapper.UserMapper;
import com.demons.shiro.model.User;
import com.demons.shiro.service.UserService;
import com.demons.shiro.util.DateUtil;

@Controller
@RequestMapping("/other")
public class OtherController {
	
	@Autowired
	private UserService usService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Resource(name = "taskExecutor")  
	private TaskExecutor taskExecutor;
	
	@RequestMapping(value="/getList")
	@ResponseBody
	public Map<String, Object> getList(String cp,String ps,String name) {
		System.out.println(cp+"1--->"+ps);
		Map<String, Object> map = new HashMap<String, Object>();
		int num = userMapper.selectListNum();
		PageBean<User> page = new PageBean<User>(Integer.parseInt(cp), Integer.parseInt(ps), num);
		
		map.put("name", name);
		List<User> selectList = usService.selectList(map,page);
		
		map.put("total", num);
		map.put("rows", selectList);
		return map;
	}
	
	@RequestMapping(value="/getUser")
	@ResponseBody
	public Map<String, Object> getUser(String cp,String ps) {
		System.out.println(cp+"--->"+ps);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("page", 2);
		map1.put("total", 6);
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("id", 1);
			map2.put("name", "小花");
			map2.put("email", "2029@qq.com");
			map2.put("status", 0);
			list.add(map2);
		}
		map1.put("rows", list);
		return map1;
	}
	
	@RequestMapping(value="/bootstrap")
	public String bootstrap() {
		return "/bootstrap";
		
	}
	
	@RequestMapping(value="/rollback")
	public String Testrollback() {
		User user = new User();
		user.setId("142");
		user.setAge(12);
		int es = usService.updateByPrimaryKeySelective(user);
		return null;
		
	}
	
	@RequestMapping(value="/te")
	@ResponseBody
	public List<Map<String, Object>> te(@RequestParam("id") String  id,@RequestParam("name") String name) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b2");
		System.out.println("id:"+id+"---name:"+name);
//		System.out.println("user:"+user);
		System.out.println("------->来了");
		list.add(map);
		return list;
		
	}
	
	@RequestMapping(value="/test")
	@ResponseBody
	public List<User> test(@RequestBody User user) {
		List<User> selectAll = usService.selectAll();
		
		System.out.println("v---->:"+selectAll);
		System.out.println(selectAll.get(0).getNa());
		return selectAll;
		
	}
	
	@RequestMapping(value="/tests")
	@ResponseBody
	public User tests(@RequestParam(value="id",required=true)String id) {
		User user = userMapper.selectOne(id);
		System.out.println("v---->:"+user);
		return user;
		
	}
	//侧接口
	/*@RequestMapping(value="testInter",method=RequestMethod.POST)
	public String testInter(@RequestBody User name) {
		System.out.println(name);
		return name.toString();
		
	}*/
	
	@RequestMapping(value="/as")
	public ModelAndView as(ModelAndView mo) {
		mo.setViewName("list");
		return mo;
	}
	
	@RequestMapping("/testInter")
	public User testInter(@RequestParam(value="na") String na) {
		System.out.println("进入");
		User name = usService.selectByPrimaryKey(na);
		return name;
	}
	
	/**
	 * 将定时注解@Scheduled和其他的映射之类的注解，@controller之类的放一起，不起作用
	 * 一般单独放一个类，本项目放在ExampleTimer中
	 * @Title: taskExecutor   
	 * @Description: TODO  
	 * @return: void      
	 * @throws
	 */
//	@Scheduled(cron="0/5 * * * * ? ")   //每5秒执行一次
	@RequestMapping(value="/taskExecutor")
	public void taskExecutor() {
		System.out.println("定时执行五秒一次:"+DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("来了来了...他们真的来了");
				
			}
		});
	}
	
}
