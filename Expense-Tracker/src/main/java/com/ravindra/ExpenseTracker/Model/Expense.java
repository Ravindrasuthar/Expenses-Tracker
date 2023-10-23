package com.ravindra.ExpenseTracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;

    private LocalDateTime dateTime;

    private Month month;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "fk-product-id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "fk-user-id")
    User user;

    public Expense(User user, Product product)
    {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.product = product;
        this.month = dateTime.getMonth();
        this.date = dateTime.toLocalDate();
    }
}
