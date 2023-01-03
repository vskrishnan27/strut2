package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.services.ProductService;


@Controller
@PropertySource("classpath:application.properties")
public class ProductController {
    
    @Autowired
    private ProductService productService;
	
	@Value("${krish.num}")
	private String num;

    @GetMapping("/spring")
    public String hello() {
    	System.out.println("spring app loaded"+num);
        return "hello";
    }

    @PostMapping("/product/addProduct")
    public ResponseEntity<String>  addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProductService(product),HttpStatus.CREATED);
    }

    @PutMapping("/product/updateStock")
    public ResponseEntity<String>  updateStock(@RequestParam int stock,@RequestParam Long id) {
        return new ResponseEntity<>(productService.updateStocks(stock,id),HttpStatus.CREATED);
    }

    @GetMapping("/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam int id) {
        return new ResponseEntity<>(productService.deleteProduct((long)id),HttpStatus.OK);
    }


    @GetMapping("/product/showProducts")
    public ResponseEntity<List<Product>> fetchAllProduct() {
        List<Product> listOfProducts = productService.fetchAllProductService();
        return new ResponseEntity<>(listOfProducts,HttpStatus.OK);
    }

    
}
