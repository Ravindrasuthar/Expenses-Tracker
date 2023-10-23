package com.ravindra.ExpenseTracker.Service;

import com.ravindra.ExpenseTracker.Model.Expense;
import com.ravindra.ExpenseTracker.Model.Product;
import com.ravindra.ExpenseTracker.Model.User;
import com.ravindra.ExpenseTracker.Model.UserAuthenticationToken;
import com.ravindra.ExpenseTracker.Model.dto.SignInInput;
import com.ravindra.ExpenseTracker.Repo.UserAuthenticationTokenRepo;
import com.ravindra.ExpenseTracker.Repo.UserRepo;
import com.ravindra.ExpenseTracker.Service.Emailer.EmailHandler;
import com.ravindra.ExpenseTracker.Service.Encrypter.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ProductService productService;

    @Autowired
    ExpenseService expenseService;

    public String UserSignUp(User user) {
        String email = user.getUserEmail();
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser!=null)
        {
            return "User already Registered try sign in !!!";
        }
        String password = user.getUserPassword();
        try {
            String encryptedpass = PasswordEncryptor.encrypt(password);
            user.setUserPassword(encryptedpass);
            userRepo.save(user);
            return "User Registered !!";
        } catch (NoSuchAlgorithmException e) {
            return "Something went wrong !!!";
        }
    }

    public String UserSignIn(SignInInput signInInput) {

        String email = signInInput.getEmail();
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser==null)
        {
            return "User not registered !!!";
        }
        String password = signInInput.getPassword();
        try {
            String encryptedpass = PasswordEncryptor.encrypt(password);
            if(existingUser.getUserPassword().equals(encryptedpass))
            {
                UserAuthenticationToken token = new UserAuthenticationToken(existingUser);
                if(EmailHandler.sendEmail(email,"otp after login",token.getTokenValue()))
                {
                    authenticationService.AddToken(token);
                    return "Check email for otp/token !!!";
                }
                else {
                    return "error while generating otp !!!";
                }
            }
            else {
                return "Invalid credentials !!!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Something went wrong !!!";
        }
    }

    public String AddExpense(String email, String tokenValue, Product product) {
        if(authenticationService.Authentication(email,tokenValue))
        {
            User user = userRepo.findFirstByUserEmail(email);
            productService.AddProduct(product);
            Expense expense = new Expense(user,product);
            expenseService.AddExpense(expense);
            return "Expense Added !!!";
        }
        else
        {
            return "Not a Valid User !!!";
        }
    }

    public int GetTotalExpensesByMonth(Month month) {
        List<Expense> expenses = expenseService.FindAllExpensesByMonth(month);
        if(expenses==null)
        {
            System.out.println("No Expenses for the given month !!!");
            return -1;
        }
        List<Product> products = new ArrayList<>();
        int sum =0;
        for(Expense exp : expenses)
        {
            Product product = exp.getProduct();
            sum += product.getProductPrice();
        }
        return sum;
    }

    public List<Product> GetAllProductForGivenDate(LocalDate date) {
        List<Expense> expenses = expenseService.findAllExpensesByDate(date);
        if(expenses==null)
        {
            System.out.println("No Products for the given date !!!");
            return null;
        }
        List<Product> products = new ArrayList<>();
        for(Expense exp : expenses)
        {
            products.add(exp.getProduct());
        }
        return products;
    }

    public List<Expense> GetAllExpenses() {
        return expenseService.GetAllExpanses();
    }

    public String DeleteExpenseById(String email, String tokenValue,Integer id) {

        if(authenticationService.Authentication(email,tokenValue))
        {
            return expenseService.DeleteExpenseById(id);
        }
        else
        {
            return "Not a valid user !!!";
        }

    }
}
