package com.ecommerce.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class authorization implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("filter destroy");
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("do filter");
		
		  HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession(true);
		
		try {
//			
			System.out.println(req.getServletPath());
			if(req.getServletPath().equals("/login/loginAction.action")) { 
				System.out.println(req.getServletPath());
				chain.doFilter(request, response);
				return;
			}
			
			if(session.getAttribute("UUID")==null) {
				System.out.println("loginPage");
				req.getRequestDispatcher("LoginPage.jsp").forward(request, response);
				return;
			}
			
			System.out.println("second filter");
			if(session.getAttribute("UUID")!=null && session.getAttribute("role").equals("user") && req.getServletPath().equals("/index.jsp")) {
				System.out.println("user panel");
				req.getRequestDispatcher("adminPage.jsp").forward(request, response);
				return;
			}
			
			if(session.getAttribute("UUID")!=null && session.getAttribute("role").equals("admin") && req.getServletPath().equals("/index.jsp")) {
				System.out.println("admin panel");
				req.getRequestDispatcher("userPage.jsp").forward(request, response);
				return;
			}
		
			System.out.println(session.getAttribute("UUID"));
			chain.doFilter(request, response);
			
			
			
		} catch (Exception e) {
			
			System.out.println(e);
			System.out.println("afyer");
	
			
		}
		
		
	
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter init");
		
	}

}
