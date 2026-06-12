package com.example.hostelfood.repository;

import com.example.hostelfood.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository
        extends JpaRepository<Review, Integer> {

    // Find reviews by food item
    List<Review> findByFoodName(String foodName);

    // Find reviews by student
    List<Review> findByStudentName(
            String studentName
    );

    // Average rating of a food item
    @Query("SELECT AVG(r.rating) FROM Review r " +
            "WHERE r.foodName = :foodName")
    Double getAverageRatingByFoodName(
            @Param("foodName") String foodName
    );

    // Top rated food items
    @Query("SELECT r.foodName, AVG(r.rating), COUNT(r) " +
            "FROM Review r " +
            "GROUP BY r.foodName " +
            "ORDER BY AVG(r.rating) DESC")
    List<Object[]> getTopRatedFoodItems();

    // Latest 5 reviews
    List<Review>
    findTop5ByOrderByIdDesc();
}