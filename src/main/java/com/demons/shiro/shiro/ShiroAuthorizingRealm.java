package com.demons.shiro.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ShiroAuthorizingRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(ShiroAuthorizingRealm.class);

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//与currentUser.login(token);对应 执行的方法跳转到此处
		System.out.println("--ShiroAuthorizingRealm-->2:"+token.hashCode());
		//1. 把AuthenticationInfo转化为UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//2. 从UsernamePasswordToken中获取username
		String username = upToken.getUsername();
		//3. 调用数据库的方法，从数据库查询username对应的用户记录
		System.out.println("从数据库获取username："+username+"所对应的的用户记录");
		//4. 若用户不存在，则可以跑出UnknownAccountException异常
		if ("unknow".equals(username)) {
			throw new UnknownAccountException("用户不存在！");
		}
		
		String credentials = "";
		//5. 根据用户的情况，决定是否跑出LockedAccountException 异常
		if ("admin".equals(username)) {
//			throw new LockedAccountException("用户被锁定！");
			credentials="c41d7c66e1b8404545aa3a0ece2006ac";
		}else if ("user".equals(username)) {
			credentials="2bbffae8c52dd2532dfe629cecfb2c85";
			
		}
		System.out.println("ShiroAuthorizingRealm------>");
		//6. 根据用户情况，来构建AuthenticationInfo 并返回。通常使用的实现类是SimpleAuthenticationInfo
		//以下信息是从数据库获取
		//1). principal认证的实体信息。可以是username，也可以是数据库对应的用户实体类对象
		String principal = username;
		// 2). credentials:密码
//		String credentials = "123";
		//3). realmName:当前realm 对象的name,调用父类的getName()即可
		String realmName = getName();
		//不使用盐值的情况
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		//4). 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		//使用盐值 构造器中肯定需要传入盐值的参数
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}

	public static void main(String[] args) {
		String algorithmName = "MD5";
		Object credentials = "123";
		Object salt = ByteSource.Util.bytes("user");//此处先用用户名来做盐值
		int hashIterations = 1024;//加密次数
		
		Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1. 从PrincipalCollection中获取用户登录信息 值对应SimpleAuthenticationInfo参数principal
		Object principal = principals.getPrimaryPrincipal();
		System.out.println("PrincipalCollection--------->"+principal);
		//2. 利用登录用户的信息判断当前用户的角色或权限（可能需要查询数据库）
		Set<String> roles = new HashSet<>();
		//不管是谁先给他分配一个user的权限
		roles.add("user");
		if ("admin".equals(principal)) {
			roles.add("admin");
		}
		//3. 创建SimpleAuthenticationInfo,并设置roles 属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		//4. 返回SimpleAuthenticationInfo对象
		return info;
	}

    
}
