package com.shiro.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * @author 林尤庆
 * @date 2018年3月22日 下午3:28:18
 * @version 1.0
 */
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.设置请求编码
		request.setCharacterEncoding("utf-8");
		
		//2.接收用户名和密码
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//3.调用login方法
		//3.1 获取Subject
		Subject subject = SecurityUtils.getSubject();
		
		AuthenticationToken token = new UsernamePasswordToken(name, password);
		
		try {
			subject.login(token);
			
			//获取Principal
			String dbName = (String)subject.getPrincipal();
			
			//把用户信息存储到session
			request.getSession().setAttribute("userName", name);
			
			//登录成功
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (UnknownAccountException e) {
			request.setAttribute("msg", "用户名不存在");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (IncorrectCredentialsException e) {
			request.setAttribute("msg", "密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
