package com.ecommerce.loginAction;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.ecommerce.model.Orders;
import com.ecommerce.model.Products;
import com.ecommerce.model.User;
import com.ecommerce.repository.dbConnection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



public class wishListAction extends ActionSupport{

		List<Products> wishList = null;
		
		public List<Products> getWishList() {
			return wishList;
		}

		public String execute() {
			
			Map<String,Object> session = null;
			
			try {
				 session = ActionContext.getContext().getSession();
					User u = (User) session.get("userInfo");
					System.out.println(u.getUUID());
					
					dbConnection db = new dbConnection();
					wishList =  db.findWishListByUUID(u.getUUID());
						 
			}catch(Exception e) {
				System.out.println("wishList execute method"+e.getMessage());
			}
			return "ok";
		}
	
}
