package com.microservice.FirstJobApp.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews(Long CompanyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review review);
}
