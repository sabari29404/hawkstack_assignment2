package com.assignment.assignment1.repository;

import com.assignment.assignment1.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepo extends JpaRepository<Rating, Long> {

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    Optional<Rating> findByUserIdAndCourseId(Long userId, Long courseId);

    List<Rating> findByCourseId(Long courseId);
}

