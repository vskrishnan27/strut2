package com.ecommerce.loginAction;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ecommerce.model.User;
import com.ecommerce.repository.dbConnection;
import com.opensymphony.xwork2.ActionContext;

public class buyAction extends Thread{

	public String execute() {
		try {
			String productId = ServletActionContext.getRequest().getParameter("productId");
			String qty = ServletActionContext.getRequest().getParameter("qty");
			dbConnection db = new dbConnection();
			Map<String,Object> session = ActionContext.getContext().getSession();
			User u = (User) session.get("userInfo");
			return db.buyProduct(productId,qty,u.getUUID());	
		} catch (Exception e) {
			return "failed";
		}
	}
}
