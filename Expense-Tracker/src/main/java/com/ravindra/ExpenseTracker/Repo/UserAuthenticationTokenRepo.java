package com.ravindra.ExpenseTracker.Repo;

import com.ravindra.ExpenseTracker.Model.UserAuthenticationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationTokenRepo extends CrudRepository<UserAuthenticationToken,Integer> {
    UserAuthenticationToken findByTokenValue(String tokenValue);
}
