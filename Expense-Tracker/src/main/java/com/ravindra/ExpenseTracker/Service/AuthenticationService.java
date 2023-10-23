package com.ravindra.ExpenseTracker.Service;

import com.ravindra.ExpenseTracker.Model.UserAuthenticationToken;
import com.ravindra.ExpenseTracker.Repo.UserAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserAuthenticationTokenRepo userAuthenticationTokenRepo;

    public boolean Authentication(String email, String tokenValue)
    {
        UserAuthenticationToken token = userAuthenticationTokenRepo.findByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getUser().getUserEmail().equals(email);
        }
        else {
            return false;
        }
    }

    public void AddToken(UserAuthenticationToken token) {
        userAuthenticationTokenRepo.save(token);
    }
}
