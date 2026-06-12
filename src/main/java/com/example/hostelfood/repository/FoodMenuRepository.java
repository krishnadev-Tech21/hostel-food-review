package com.example.hostelfood.repository;

import com.example.hostelfood.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodMenuRepository
        extends JpaRepository<
        FoodMenu,
        Integer
        > {

    // Find menu by date
    List<FoodMenu>
    findByMenuDate(
            LocalDate date
    );

    // Find menu by date + meal type
    List<FoodMenu>
    findByMenuDateAndMealType(
            LocalDate date,
            String mealType
    );

    // Find special menus
    List<FoodMenu>
    findByIsSpecialTrue();

    // Find menus between dates
    List<FoodMenu>
    findByMenuDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}