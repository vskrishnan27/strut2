package com.ecommerce.loginAction;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.ecommerce.model.Orders;
import com.ecommerce.model.Products;
import com.ecommerce.model.User;
import com.ecommerce.repository.dbConnection;
import com.opensymphony.xwork2.ActionContext;

public class showProductAction {

	List<Products> productList = null;
	

	public List<Products> getProductList() {
		return productList;
	}

	public String execute() {
		
		
		try {
				dbConnection db = new dbConnection();
				productList =  db.showProducts();
		}catch(Exception e) {	
			System.out.println("orderhistiry execute method"+e.getMessage());
		}
		return "ok";
		
	}


	
	
}
