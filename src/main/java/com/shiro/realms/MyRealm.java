package com.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by lyq on 2018/3/22.
 */

public class MyRealm extends AuthorizingRealm{
    //授权方法：获取授权信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权方法...");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//添加资源授权码
		info.addStringPermission("product:add");
		
		//添加角色授权码
		info.addRole("admin");
		
        return info;
    }
    //认证方法：获取认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行授权方法...");

        //1.获取用户输入的账户信息
      	UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
      		
      	//模拟数据库的密码
      	String name = "jack";
      	String password = "1234";
      		
      	if(!token.getUsername().equals(name)){
      		//用户不存在
      		return null;
      	}
      	
      	//返回密码
      	return new SimpleAuthenticationInfo(name,password,"");
    }
}
