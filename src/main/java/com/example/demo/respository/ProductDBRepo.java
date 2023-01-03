package com.example.demo.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.Products;
import com.example.demo.model.Product;


@Repository
public class ProductDBRepo {
	
	
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
	
	
//	get all products list
	public List<Product> showProducts(){
		final String showProductsQuery = "select * from products;";
		if(conn==null) {
			connectToDB();
		}
		List<Product> productList = new ArrayList<>();
		  try {
			  PreparedStatement ps=conn.prepareStatement(showProductsQuery);  
			  ResultSet rs=ps.executeQuery(); 
			  while(rs.next()){  
				  System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
				  Product product = new Product(rs.getString(3),rs.getLong(1),rs.getInt(2),rs.getInt(4));
				  productList.add(product);  
			  }  
			 return productList;
		} catch (SQLException e) {
			System.out.println("sql exception while fetchingbut action of a user==>"+e.getMessage());
			return null;
		}  catch(Exception e) {
			System.out.println("exception in buy action");
			return null;
		}
	}

	public String addProductToDb(String productName, int stock, int price) {
		final String addProductQuery = "insert into products (product_name,stock,price) values(?,?,?);";
		if(conn==null) {
			connectToDB();
		}
	
		  try {
				PreparedStatement ps=conn.prepareStatement(addProductQuery);  
				ps.setString(1,productName);
				ps.setInt(2,stock);
				ps.setInt(3,price);
				ps.executeUpdate(); 
				return "SUCCESS";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "FAILED";
	}

	public String updateStock(int stock, Long id) {
		final String updateStockQuery = "UPDATE products p SET p.stock = p.stock + ? WHERE p.product_id=?;";
		  try {
				PreparedStatement ps=conn.prepareStatement(updateStockQuery);  
				ps.setInt(1,stock);
				ps.setLong(2,id);
				ps.executeUpdate(); 
				return "SUCCESS";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "FAILED";
	}

	public String deleteById(Long id) {
		final String deleteByIdQuery = "delete from products where product_id=?;";
		  try {
				PreparedStatement ps=conn.prepareStatement(deleteByIdQuery);  
				ps.setLong(1,id);
				ps.executeUpdate(); 
				return "SUCCESS";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "FAILED";
	}	
		
}
