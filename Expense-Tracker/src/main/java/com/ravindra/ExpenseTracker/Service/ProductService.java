package com.ravindra.ExpenseTracker.Service;

import com.ravindra.ExpenseTracker.Model.Product;
import com.ravindra.ExpenseTracker.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public String AddProduct(Product product) {
        productRepo.save(product);
        return "Product added";
    }

}
