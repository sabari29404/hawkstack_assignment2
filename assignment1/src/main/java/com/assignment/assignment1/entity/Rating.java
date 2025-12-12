package com.assignment.assignment1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "course_ratings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"}))
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long courseId;
    private int rating;
}

