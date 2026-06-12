package com.example.hostelfood.controller;

import com.example.hostelfood.entity.Review;
import com.example.hostelfood.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.hostelfood.dto.FoodRatingStats;
import java.util.List;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    // GET top rated foods
// URL: GET /api/reviews/top-rated
    @GetMapping("/top-rated")
    public ResponseEntity<List<FoodRatingStats>>
    getTopRated() {

        return ResponseEntity.ok(
                reviewService.getTopRatedFoods()
        );
    }


    // GET recent reviews
// URL: GET /api/reviews/recent
    @GetMapping("/recent")
    public ResponseEntity<List<Review>>
    getRecent() {

        return ResponseEntity.ok(
                reviewService.getRecentReviews()
        );
    }


    // GET overall average rating
// URL: GET /api/reviews/average
    @GetMapping("/average")
    public ResponseEntity<Double>
    getAverage() {

        return ResponseEntity.ok(
                reviewService.getOverallAverageRating()
        );
    }




    // GET all reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // GET one review by ID
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // CREATE new review
    @PostMapping
    public Review createReview(
            @RequestBody Review review
    ) {
        return reviewService.createReview(review);
    }

    // UPDATE review
    @PutMapping("/{id}")
    public Review updateReview(
            @PathVariable Long id,
            @RequestBody Review review
    ) {
        return reviewService.updateReview(
                id,
                review
        );
    }

    // DELETE review
    @DeleteMapping("/{id}")
    public String deleteReview(
            @PathVariable Long id
    ) {
        return reviewService.deleteReview(id);
    }
}