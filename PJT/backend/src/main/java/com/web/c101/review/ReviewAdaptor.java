package com.web.c101.review;

import java.time.LocalDateTime;

public class ReviewAdaptor {
    public static ReviewDto entityToDto(Review review) {
        return ReviewDto.builder()
                .mid(review.getMid())
                .dong(review.getDong())
                .store(review.getStore())
                .content(review.getContent())
                .star_score(review.getStar_score())
                .flag(review.getFlag())
                .build();
    }

    public static Review dtoToEntity(ReviewDto reviewDto) {
        return Review.builder()
                .mid(reviewDto.getMid())
                .dong(reviewDto.getDong())
                .store(reviewDto.getStore())
                .content(reviewDto.getContent())
                .star_score(reviewDto.getStar_score())
                .reg_date(LocalDateTime.now())
                .flag(reviewDto.getFlag())
                .build();
    }
}
