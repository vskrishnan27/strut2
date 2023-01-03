package com.ecommerce.loginAction;


import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.ecommerce.model.User;
import com.ecommerce.repository.dbConnection;


import com.opensymphony.xwork2.ActionContext;

import com.opensymphony.xwork2.ModelDriven;


public class loginAction  implements ModelDriven<User>,ServletRequestAware{

	private String username;
	private String password;
	private String UUID;
	
	private User user;
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
	
	protected HttpServletRequest request;
	
	public  String execute()  {

		
		try {
			 Map<String,Object> session = null;
			 session = ActionContext.getContext().getSession();
				try {
					dbConnection db = new dbConnection();
					System.out.println(username+"^^^^^"+password+"++++");
					User user = db.checkLogin(username,password);
					setUser(user);
					if(user==null ) {
						System.out.println("no users found ==>username / pswrd may be wrong");
						return "invalid user / check your credentials";
					}
					session.put("userInfo",user);
					session.put("UUID", user.getUUID());
					session.put("role", user.getRole());
					System.out.println("user returned");
					return "user";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "failed";	
				}
		}catch(Exception e) {
			System.out.println("loginaction");
			return "failed";
		}	
	}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
		
	}
	
	

	public String logout() {
		
		Map<String, Object> sessionMap=ActionContext.getContext().getSession();
		sessionMap.remove("UUID");
		sessionMap.remove("role");
		sessionMap.remove("userInfo");
		System.out.println("Logged out successfully");
		return "LOGOUT";
	}
	
}


