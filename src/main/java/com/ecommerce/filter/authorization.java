package com.ecommerce.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class authorization implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("filter destroy");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("do filter");
		
		  HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession(true);
		
		try {
			
			if(session.getAttribute("UUID")!=null) {
				req.getRequestDispatcher("welcome.jsp").forward(request, response);
				return;
			}
			System.out.println(session.getAttribute("UUID"));
			chain.doFilter(request, response);
			
			
			
//			chain.doFilter(request, response);
//			System.out.println(session);
		} catch (Exception e) {
			
			System.out.println( "<====>"+e.getMessage());
			chain.doFilter(request, response);
			
		}
		
		
	
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter init");
		
	}

}
