package com.ravindra.ExpenseTracker.Service;

import com.ravindra.ExpenseTracker.Model.Expense;
import com.ravindra.ExpenseTracker.Repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepo expenseRepo;

    public void AddExpense(Expense expense) {
        expenseRepo.save(expense);
    }

    public List<Expense> FindAllExpensesByMonth(Month month) {
        List<Expense> expenses = expenseRepo.findAllByMonth(month);
        return expenses;
    }

    public List<Expense> findAllExpensesByDate(LocalDate date) {
        List<Expense> expenses = expenseRepo.findAllByDate(date);
        return expenses;
    }

    public List<Expense> GetAllExpanses() {
        return (List<Expense>) expenseRepo.findAll();
    }

    public String DeleteExpenseById(Integer id) {
        expenseRepo.deleteById(id);
        return "expanse deleted !!!";
    }
}
