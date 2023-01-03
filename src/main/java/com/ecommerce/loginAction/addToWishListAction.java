package com.ecommerce.loginAction;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ecommerce.model.User;
import com.ecommerce.repository.dbConnection;
import com.opensymphony.xwork2.ActionContext;

public class addToWishListAction {
	private String status;
	public String getStatus() {
		return status;
	}
	public String execute() {
		try {
			String productId = ServletActionContext.getRequest().getParameter("productId");
			dbConnection db = new dbConnection();
			Map<String,Object> session = ActionContext.getContext().getSession();
			User u = (User) session.get("userInfo");
			status=db.addToWishList(productId,u.getUUID());
			return "ok";
	
		}catch(Exception e) {
			System.out.println(e.getMessage()+"<===add to wishlist");
			status=e.getMessage();
			return "ok";
		}
		
	}
}
