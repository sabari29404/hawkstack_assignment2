package com.assignment.assignment1.service;

import com.assignment.assignment1.entity.Rating;
import com.assignment.assignment1.repository.EnrollmentRepo;
import com.assignment.assignment1.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    // Rule: Only enrolled users can rate
    public String rateCourse(Long userId, Long courseId, int rating) {

        // Check if user is enrolled
        if (!enrollmentRepo.existsByUserIdAndCourseId(userId, courseId)) {
            return "User must enroll before rating";
        }

        // Check if rating already exists (idempotent update)
        Optional<Rating> existing = ratingRepo.findByUserIdAndCourseId(userId, courseId);

        if (existing.isPresent()) {
            Rating r = existing.get();
            r.setRating(rating);
            ratingRepo.save(r);
            return "Rating updated successfully (idempotent)";
        }

        // New rating
        Rating r = new Rating();
        r.setUserId(userId);
        r.setCourseId(courseId);
        r.setRating(rating);
        ratingRepo.save(r);

        return "Rating submitted successfully";
    }

    public double getCourseRating(Long courseId) {
        List<Rating> ratings = ratingRepo.findByCourseId(courseId);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        return ratings.stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);
    }
}

