package com.example.hostelfood.service;

import com.example.hostelfood.dto.FoodRatingStats;
import java.util.ArrayList;
import java.util.List;
import com.example.hostelfood.entity.Review;
import com.example.hostelfood.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Get top rated food items
    public List<FoodRatingStats> getTopRatedFoods() {

        List<Object[]> results =
                reviewRepository.getTopRatedFoodItems();

        List<FoodRatingStats> stats =
                new ArrayList<>();

        for (Object[] row : results) {

            String foodName =
                    (String) row[0];

            double avgRating =
                    ((Number) row[1]).doubleValue();

            long reviewCount =
                    ((Number) row[2]).longValue();

            avgRating =
                    Math.round(avgRating * 10.0) / 10.0;

            String stars =
                    buildStarString(avgRating);

            stats.add(
                    new FoodRatingStats(
                            foodName,
                            avgRating,
                            reviewCount,
                            stars
                    )
            );
        }

        return stats;
    }


    // Get recent reviews
    public List<Review> getRecentReviews() {

        return reviewRepository
                .findTop5ByOrderByIdDesc();
    }


    // Get overall average rating
    public double getOverallAverageRating() {

        List<Review> all =
                reviewRepository.findAll();

        if (all.isEmpty()) {
            return 0.0;
        }

        double sum =
                all.stream()
                        .mapToInt(Review::getRating)
                        .sum();

        double avg =
                sum / all.size();

        return Math.round(avg * 10.0) / 10.0;
    }


    // Helper method
    private String buildStarString(
            double rating) {

        int full = (int) rating;

        String stars =
                "⭐".repeat(
                        Math.min(full, 5)
                );

        return stars +
                " (" +
                rating +
                ")";
    }

    // GET all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // GET one review by ID
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id.intValue())
                .orElse(null);
    }

    // CREATE new review
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    // UPDATE existing review
    public Review updateReview(Long id, Review updatedReview) {

        Review existingReview =
                reviewRepository.findById(id.intValue())
                        .orElse(null);

        if (existingReview == null) {
            return null;
        }

        existingReview.setStudentName(
                updatedReview.getStudentName()
        );

        existingReview.setFoodName(
                updatedReview.getFoodName()
        );

        existingReview.setRating(
                updatedReview.getRating()
        );

        existingReview.setComment(
                updatedReview.getComment()
        );

        return reviewRepository.save(existingReview);
    }

    // DELETE review
    public String deleteReview(Long id) {

        Review existingReview =
                reviewRepository.findById(id.intValue())
                        .orElse(null);

        if (existingReview == null) {
            return "Review not found!";
        }

        reviewRepository.deleteById(id.intValue());

        return "Review deleted successfully!";
    }
}