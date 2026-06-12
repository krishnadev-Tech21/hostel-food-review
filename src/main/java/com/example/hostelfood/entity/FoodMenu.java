package com.example.hostelfood.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "food_menu")
public class FoodMenu {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private int id;

    @Column(
            name = "meal_type",
            nullable = false
    )
    private String mealType;

    @Column(
            name = "items",
            nullable = false
    )
    private String items;

    @Column(
            name = "menu_date",
            nullable = false
    )
    private LocalDate menuDate;

    @Column(name = "description")
    private String description;

    @Column(name = "is_special")
    private boolean isSpecial;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {

        this.createdAt =
                LocalDateTime.now();

        // Default today
        if (this.menuDate == null) {

            this.menuDate =
                    LocalDate.now();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(
            int id
    ) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(
            String mealType
    ) {
        this.mealType = mealType;
    }

    public String getItems() {
        return items;
    }

    public void setItems(
            String items
    ) {
        this.items = items;
    }

    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(
            LocalDate menuDate
    ) {
        this.menuDate =
                menuDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(
            String description
    ) {
        this.description =
                description;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(
            boolean special
    ) {
        isSpecial =
                special;
    }

    public LocalDateTime
    getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt
    ) {
        this.createdAt =
                createdAt;
    }
}