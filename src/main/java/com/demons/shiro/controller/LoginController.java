package com.demons.shiro.controller;

import com.demons.shiro.annotation.ExecTime;
import com.demons.shiro.mapper.UserMapper;
import com.demons.shiro.model.User;
import com.demons.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@RequestMapping(value="/")
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value="/shiro/login")
	public String login(HttpServletRequest request,@RequestParam("username") String username,@RequestParam("password") String password) {
		//获取用户对象
		Subject currentUser = SecurityUtils.getSubject();
//		 System.out.println("oooo"+currentUser.getPrincipal().toString());
		 if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	            token.setRememberMe(true);
	            try {
	            	//这个方法其实进入了ShiroAuthorizingRealm类中的doGetAuthenticationInfo方法
	            	System.out.println("---->1:"+token.hashCode());
	                currentUser.login(token);
	    		 	return "/list";
	            }
	            // ... catch more exceptions here (maybe custom ones specific to your application?
	            catch (AuthenticationException ae) {
	                //unexpected condition?  error?
	            	System.out.println("登录失败："+ae.getMessage());
	            }
	        }
		 	
		 	return "/list";
		 
	}
	
	public static String getMessage(HttpServletRequest request, String key){
        Locale currentLocale = RequestContextUtils.getLocale(request);
        String lang = currentLocale.getLanguage();
        ResourceBundle bundle = ResourceBundle.getBundle("messages_"+lang, currentLocale);
        return bundle.getString(key);
    }
	
	@RequestMapping(value="/login")
	public String login() {
		System.out.println("登录方法。。。。。。。");
		return "/login";
		
	}
	
	@RequestMapping(value="/shiro/logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("注销账号！！");
		currentUser.logout();
		return "/login";
	}
	
	@RequestMapping(value="/index")
	public String index() {
		System.out.println("成功。。。。。。。");
		return "/index";
		
	}
	
	@RequestMapping(value="/vue")
	public String vue() {
		return "/vue/demo01";
	}
	
	
	@RequestMapping(value="/unauthorized")
	public String unauthorized() {
		return "/unauthorized";
	}

	@ExecTime
	@RequestMapping(value="/user")
	public String user() throws IOException {
		userService.test();
		System.out.println("进入-------->");
		
		return "/user";
	}
	
	@RequestMapping(value="/admin")
	public String admin() {
//		User selectByPrimaryKey = userService.selectByPrimaryKey("1");
//		System.out.println("------>"+selectByPrimaryKey.getName());
		return "/admin";
	}
	
	@RequestMapping(value="/yy")
	@ResponseBody
	public User test() {
		User selectByPrimaryKey = userService.selectByPrimaryKey("1");
		return selectByPrimaryKey;
		
	}
	
	
}
