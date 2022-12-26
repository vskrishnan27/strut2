package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ecommerce.model.Orders;
import com.ecommerce.model.Products;
import com.ecommerce.model.User;

public class dbConnection {
	
	private  Connection conn;
	
	private  void connectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","Krishnan@98");  
			if(conn!=null) System.out.println("Db Connected");		
		} catch (SQLException e) {
			System.out.println("Db connection failed");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}
	
	public Connection getDbConnection() {
		if(conn==null) connectToDB();
		return conn;
	}
	
	
	
	
	// findWishListByUUID to get the wishlist of the users
	final String wishListQuery = "SELECT w.p_id AS product_id,product_name,price,stock FROM wish_list w  INNER JOIN products p ON w.p_id = p.product_id WHERE w.uuid = ?;";
	
	public List<Products> findWishListByUUID(String UUID){
		if(conn==null) connectToDB();
		List<Products> wishList = new ArrayList<>();
		  
				   try {
					
					  PreparedStatement ps=conn.prepareStatement(wishListQuery);  
					  ps.setString(1,UUID);
					  ResultSet rs=ps.executeQuery(); 
					  
					  while(rs.next()){  
						  System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
						  Products product = new Products(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)));
						  
						  wishList.add(product);  
					  }  
					  
					  
					  
					  
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("sql exception while fetching wishlist history of a user==>"+e.getMessage());
				}  catch(Exception e) {
					System.out.println("exception in wishlist");
				}
		 
		
		return wishList;
	}
	
	
	
//	checking the login credentials are right/wrong
	final String loginQuery = "select name,uuid AS UUID,role,phone FROM users WHERE email=? AND pswrd = ?;";
	
	public  User checkLogin(String email,String password){
		if(conn==null) connectToDB();
		
		  
				   try {
					  PreparedStatement ps=conn.prepareStatement(loginQuery);  
					  ps.setString(1,email);
					  ps.setString(2,password);
					  ResultSet rs=ps.executeQuery(); 				  
					  if(!rs.next()) return null;
						  String name = rs.getString("name");
						  String UUID = rs.getString("UUID");
						 String role = rs.getString("role");
						 String phone = rs.getString("phone");
						 System.out.println(phone);
						 
						  return new User(name,null,UUID,role,phone);
					  
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("sql exception while fetching order login of a user");
					System.out.println(e.getMessage());
				}  catch(Exception e) {
					System.out.println("exception in login");
				}
		 
		
		return null;
	}
	
	
	
	final String orderHistoryQuery = "select p.product_name,p.price,o.qty from orders o inner join products p on o.p_id=p.product_id where uuid=?;";
	
	public List<Orders> findOrderHistoryByUUID(String UUID){
		if(conn==null) connectToDB();
		List<Orders> orderHistory = new ArrayList<>();
		  
				   try {
					
					  PreparedStatement ps=conn.prepareStatement(orderHistoryQuery);  
					  ps.setString(1,UUID);
					  ResultSet rs=ps.executeQuery(); 
					  
					  while(rs.next()){  
						  System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
						  Orders order = new Orders(rs.getString(1),rs.getInt(2),rs.getInt(3));
						  orderHistory.add(order);  
					  }  
					  
					  
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("sql exception while fetching order history of a user==>"+e.getMessage());
				}  catch(Exception e) {
					System.out.println("exception in orderhistory");
				}
		 
		
		return orderHistory;
	}
	
	
	// add wishlist for the user
	final String addWishListQuery ="insert into wish_list values(?,?);";
	public String addToWishList(String productId,String UUID) {
		if(conn==null) connectToDB();
		  try {
				
			  PreparedStatement ps=conn.prepareStatement(addWishListQuery);  
			  ps.setString(1,productId);
			  ps.setString(2,UUID);
			  
			  System.out.println(ps);
			 ps.executeUpdate(); 
			  
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql exception while fetching order history of a user==>"+e.getMessage());
			return "failed to add to wish List";
		}  catch(Exception e) {
			System.out.println("exception in orderhistory");
			return "failed to add to wish List";
		}

		return "wish list added successfully";
	}
	
	
	final String addToOrderHistoryQuery =  "INSERT INTO orders (p_id,qty,uuid) VALUES(?,?,?);";
	final String decreaseStock = "UPDATE products p SET p.stock = p.stock - ? WHERE p.product_id=?;";

	public String buyProduct(String productId, String qty, String uuid) {
		// TODO Auto-generated method stub
		if(conn==null) connectToDB();
		  try {
				
			  PreparedStatement ps=conn.prepareStatement(addToOrderHistoryQuery);  
			  ps.setString(1,productId);
			  ps.setString(2,qty);
			  ps.setString(3,uuid);
			  System.out.println(ps);
			  ps.executeUpdate(); 
			  System.out.println(ps);
			  PreparedStatement ps1=conn.prepareStatement(decreaseStock);
			  ps1.setString(1, qty);
			  ps1.setString(2,productId);
			 ps1.executeUpdate(); 
			 
			 
			 
			 return "SUCCESS";
			  
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql exception while fetchingbut action of a user==>"+e.getMessage());
			return "failed to add tobuy actiob";
		}  catch(Exception e) {
			System.out.println("exception in buy action");
			return "failed to add to buy action";
		}
		  
		 
		
		
	}

	
	
	
	
	
	
	
}
