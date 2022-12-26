package com.ecommerce.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.sql.Connection;

public class dbConnectionInterceptor implements Interceptor{

	private static Connection conn;
	
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
		
		if(conn==null )
		
		
		
		
		return "ok";
		return null;
	}

	
	
}
