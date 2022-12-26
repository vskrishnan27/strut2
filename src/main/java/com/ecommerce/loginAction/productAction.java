package com.ecommerce.loginAction;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class productAction extends ActionSupport implements SessionAware{
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void setSession(Map<String, Object> s) {
		// TODO Auto-generated method stub
		
	}
	
	
	public String execute() {
		
		try {
			 ActionContext.getContext().getSession();
		}catch(Exception e) {
			System.out.println("jahsgrf");
		}
		
	      
//		User user = new User("Krishnan","vaueKrish");
//		session.put("key", user);
		
		
		return null;
		
		
		
	}
	

	
}

	

	
