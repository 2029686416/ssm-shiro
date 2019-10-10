package com.demons.shiro.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demons.shiro.mapper.UserMapper;
import com.demons.shiro.model.User;
import com.demons.shiro.util.Encodes;



public class SecondRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(SecondRealm.class);

    @Autowired
    private UserMapper userMapper;
    
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//与currentUser.login(token);对应 执行的方法跳转到此处
		System.out.println(getName()+"--SecondRealm-->:"+token.hashCode());
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
			credentials="60b0cd4b93b1c7ed936782d3114339f1f6d8223b";
		}else if ("user".equals(username)) {
			credentials="9202ab270076b7ad4518fce49870e253ac70eba5";
		}
		System.out.println("SecondRealm------>");
		//6. 根据用户情况，来构建AuthenticationInfo 并返回。通常使用的实现类是SimpleAuthenticationInfo
		//以下信息是从数据库获取
		//1). principal认证的实体信息。可以是username，也可以是数据库对应的用户实体类对象
		String principal = username;
		// 2). credentials:密码
//		String credentials = "123";
		//3). realmName:当前realm 对象的name,调用父类的getName()即可
		String realmName = getName();
		//不实用盐值的情况
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		//4). 盐值
		User us = userMapper.selectByPrimaryKey("1");
		byte[] salt = Encodes.decodeHex(us.getSalt());
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
		//使用盐值 构造器中肯定需要传入盐值的参数
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}

	public static void main(String[] args) {
		String algorithmName = "SHA1";
		Object credentials = "123";
		Object salt = ByteSource.Util.bytes("user");//此处先用用户名来做盐值
		int hashIterations = 1024;//加密次数
		
		Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
		System.out.println(result);
		String pass = Encodes.getSaltAndPass(credentials.toString());
		System.out.println(pass);
		
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
