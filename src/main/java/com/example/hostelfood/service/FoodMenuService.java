package com.example.hostelfood.service;

import com.example.hostelfood.entity.FoodMenu;
import com.example.hostelfood.repository.FoodMenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodMenuService {

    @Autowired
    private FoodMenuRepository
            foodMenuRepository;

    // Get today's menu
    public List<FoodMenu>
    getTodayMenu() {

        LocalDate today =
                LocalDate.now();

        return
                foodMenuRepository
                        .findByMenuDate(
                                today
                        );
    }

    // Get menu by date
    public List<FoodMenu>
    getMenuByDate(
            LocalDate date
    ) {

        return
                foodMenuRepository
                        .findByMenuDate(
                                date
                        );
    }

    // Get meal by type
    public List<FoodMenu>
    getMealByType(
            String mealType
    ) {

        LocalDate today =
                LocalDate.now();

        return
                foodMenuRepository
                        .findByMenuDateAndMealType(
                                today,
                                mealType
                                        .toUpperCase()
                        );
    }

    // Add menu item
    public FoodMenu
    addMenuItem(
            FoodMenu foodMenu
    ) {

        return
                foodMenuRepository
                        .save(foodMenu);
    }

    // Update menu item
    public FoodMenu
    updateMenuItem(
            Integer id,
            FoodMenu updated
    ) {

        FoodMenu existing =
                foodMenuRepository
                        .findById(id)
                        .orElse(null);

        if (existing == null) {

            return null;
        }

        existing.setMealType(
                updated.getMealType()
        );

        existing.setItems(
                updated.getItems()
        );

        existing.setMenuDate(
                updated.getMenuDate()
        );

        existing.setDescription(
                updated.getDescription()
        );

        existing.setSpecial(
                updated.isSpecial()
        );

        return
                foodMenuRepository
                        .save(existing);
    }

    // Delete menu item
    public String
    deleteMenuItem(
            Integer id
    ) {

        if (!foodMenuRepository
                .existsById(id)) {

            return
                    "Menu item not found!";
        }

        foodMenuRepository
                .deleteById(id);

        return
                "Menu item deleted successfully!";
    }

    // Get all menus
    public List<FoodMenu>
    getAllMenus() {

        return
                foodMenuRepository
                        .findAll();
    }

    // Get this week's menu
    public List<FoodMenu>
    getWeekMenu() {

        LocalDate today =
                LocalDate.now();

        LocalDate weekEnd =
                today.plusDays(6);

        return
                foodMenuRepository
                        .findByMenuDateBetween(
                                today,
                                weekEnd
                        );
    }
}