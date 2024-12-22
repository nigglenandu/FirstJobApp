package com.microservice.FirstJobApp.Review;

import com.microservice.FirstJobApp.Company.Company;
import com.microservice.FirstJobApp.Company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired
    private ReviewRepository repo;

    @Autowired
    private ICompanyService iCompanyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = repo.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = iCompanyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            repo.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = repo.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (iCompanyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(iCompanyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            repo.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }


}