//package com.example.demo.respository;
//
//import java.util.ArrayList;
//
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.annotation.QueryAnnotation;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.demo.model.Product;
//
//
//@Repository
//@PropertySource("classpath:application.properties")
//public interface ProductRepository extends JpaRepository<Product,Long>{
//   
//	
//	
//    @Modifying
//    @Transactional
//    @Query(value="UPDATE products p SET p.stock = p.stock + ?1 WHERE p.product_id=?2",nativeQuery = true)
//    public void updateStock(int stock,Long id) ;
//        
//    
//
//}
