package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.respository.ProductDBRepo;

@Service
public class ProductService {
    
    @Autowired
    private ProductDBRepo productRepo;
    

    
    public String addProductService(Product product){ //to add product 
        try {  	
            return productRepo.addProductToDb(product.getProductName(),product.getStock(),product.getPrice());
        } catch (Exception e) {
            return "SOMETHING WENT WRONG";
        }  
    }

    public List<Product> fetchAllProductService(){ // fetch all the products form db service
        try {
            return productRepo.showProducts();
        } catch (Exception e) {
            return null;
        }
        
    }
    

    public String updateStocks(int stock,Long id){ // to update the stock it will increase the int stock value 
        try {
            return productRepo.updateStock(stock, id);
        } catch (Exception e) {
            // TODO: handle all exception
            return "something went wrong";
        }
        
    }

    public String deleteProduct(Long id){ // delete product from db
        try {       
            return productRepo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            return "no product associated with this id";
        } 
        catch (Exception e) {
            // TODO: handle exception
            return "something went wrong";
        } 
    }
}
