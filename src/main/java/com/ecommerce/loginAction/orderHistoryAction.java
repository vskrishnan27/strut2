package com.ecommerce.loginAction;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.ecommerce.model.Orders;
import com.ecommerce.model.Products;
import com.ecommerce.model.User;
import com.ecommerce.repository.dbConnection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Action(value="orderHistoryAction",results={@Result(name="ok",type="json")})  
public class orderHistoryAction extends ActionSupport implements ServletRequestAware{

		List<Orders> orderHistory = null;
		

		

		public List<Orders> getOrderHistory() {
			return orderHistory;
		}

		protected HttpServletRequest request;


		public String execute() {
			
			Map<String,Object> session = null;
			
			try {
				 session = ActionContext.getContext().getSession();
					User u = (User) session.get("userInfo");
					System.out.println(u.getUUID());
					
					dbConnection db = new dbConnection();
					orderHistory =  db.findOrderHistoryByUUID(u.getUUID());
						 
			}catch(Exception e) {
				
				
				
				System.out.println("orderhistiry execute method"+e.getMessage());
			}
			
			
			
			return "ok";
			
		}


		

		@Override
		public void setServletRequest(HttpServletRequest request) {
			// TODO Auto-generated method stub
			this.request = request;
			
		}
	
}
