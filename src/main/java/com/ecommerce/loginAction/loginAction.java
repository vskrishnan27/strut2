package com.ecommerce.loginAction;


import java.net.http.HttpRequest;
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
	public String getUUID() {
		return UUID;
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
					User user = db.checkLogin(username,password);
					setUser(user);
					if(user==null )
						System.out.println("no users found ==>username / pswrd may be wrong");
//						return "fail";
						
					
					 request = ServletActionContext.getRequest();
					request.setAttribute("UUID", user.getUUID());
					request.setAttribute("role", user.getRole());
					
					session.put("userInfo",user);
					session.put("UUID", user.getUUID());
					session.put("just", "summa in logub");
					
					
	
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "ok"; 
		}catch(Exception e) {
			System.out.println("loginaction");
		}
		

		return "ok";
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
	
	
	
	
	
}


