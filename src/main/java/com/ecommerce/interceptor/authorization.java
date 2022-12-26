package com.ecommerce.interceptor;


import com.ecommerce.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;



import org.apache.struts2.interceptor.SessionAware;  



 

public class authorization implements Interceptor,SessionAware{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		
			System.out.println("im in interceptor");
		
		
		 ai.invoke();
		 
		 
		 Map<String,Object> session = null;
		  
		   try {
				session = ai.getInvocationContext().getSession();
		   }catch(Exception e) {
			   System.out.println("im error");
		   }

		
		User u = (User) session.get("key");
		 System.out.println(u.getUsername()+"-->after"+u.getPassword());
		
		 
		
		return null;
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
