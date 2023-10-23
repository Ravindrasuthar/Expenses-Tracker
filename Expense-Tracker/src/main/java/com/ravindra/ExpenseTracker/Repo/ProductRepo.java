package com.ravindra.ExpenseTracker.Repo;

import com.ravindra.ExpenseTracker.Model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product,Integer> {
}
