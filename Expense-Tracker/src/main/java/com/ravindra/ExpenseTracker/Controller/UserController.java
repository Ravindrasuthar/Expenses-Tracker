package com.ravindra.ExpenseTracker.Controller;


import com.ravindra.ExpenseTracker.Model.Expense;
import com.ravindra.ExpenseTracker.Model.Product;
import com.ravindra.ExpenseTracker.Model.User;
import com.ravindra.ExpenseTracker.Model.dto.SignInInput;
import com.ravindra.ExpenseTracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public String UserSignUp(@RequestBody User user)
    {
        return userService.UserSignUp(user);
    }

    @PostMapping("signin")
    public String UserSignIn(@RequestBody SignInInput signInInput)
    {
        return userService.UserSignIn(signInInput);
    }

    @PostMapping("expense")
    public String AddExpense(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Product product)
    {
        return userService.AddExpense(email,tokenValue,product);
    }

    @GetMapping("all/expenses")
    public List<Expense> GetAllExpenses()
    {
        return userService.GetAllExpenses();
    }

    @DeleteMapping("expense/by/id")
    public String DeleteExpenseById(@RequestParam String email, @RequestParam String tokenValue,@RequestParam Integer id)
    {
        return userService.DeleteExpenseById(email,tokenValue,id);
    }

    @GetMapping("expenses/by/month")
    public int GetTotalExpensesByMonth(@RequestParam Month month)
    {
        return userService.GetTotalExpensesByMonth(month);
    }

    @GetMapping("all/products")
    public List<Product> GetAllProductForGivenDate(@RequestParam LocalDate date)
    {
        return userService.GetAllProductForGivenDate(date);
    }
}
