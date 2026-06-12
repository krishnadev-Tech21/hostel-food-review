package com.example.hostelfood.dto;

public class FoodRatingStats {

    private String foodItem;
    private double averageRating;
    private long reviewCount;
    private String ratingStars;

    public FoodRatingStats(
            String foodItem,
            double averageRating,
            long reviewCount,
            String ratingStars) {

        this.foodItem = foodItem;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.ratingStars = ratingStars;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(String ratingStars) {
        this.ratingStars = ratingStars;
    }
}