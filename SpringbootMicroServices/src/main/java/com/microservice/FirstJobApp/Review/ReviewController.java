package com.microservice.FirstJobApp.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cpompanies/{companyId}")
public class ReviewController {
    @Autowired
    private IReviewService service;

    @GetMapping("reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(service.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping("saveReview")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,
                                            @RequestBody Review review) {
        boolean isReviewSaved = service.addReview(companyId, review);
        if (isReviewSaved)
            return new ResponseEntity<>("Review added Successfully",
                    HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Saved",
                    HttpStatus.NOT_FOUND);
    }

    @GetMapping("reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId) {
        return new ResponseEntity<>(service.getReview(companyId, reviewId),
                HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviwId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = service.updateReview(companyId,
                                                             reviewId, review);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully",
                    HttpStatus.OK);
        else
            return new ResponseEntity<>("Review updated successfully",
                    HttpStatus.NOT_FOUND);

    }
}

