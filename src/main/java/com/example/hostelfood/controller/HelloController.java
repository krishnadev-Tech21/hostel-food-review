package com.example.hostelfood.controller;

import com.example.hostelfood.entity.Review;
import com.example.hostelfood.repository.ReviewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final ReviewRepository reviewRepository;

    public HelloController(
            ReviewRepository reviewRepository
    ) {
        this.reviewRepository = reviewRepository;
    }


}