package com.ravindra.ExpenseTracker.Repo;

import com.ravindra.ExpenseTracker.Model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Repository
public interface ExpenseRepo extends CrudRepository<Expense,Integer> {
    List<Expense> findAllByMonth(Month month);

    List<Expense> findAllByDate(LocalDate date);
}
