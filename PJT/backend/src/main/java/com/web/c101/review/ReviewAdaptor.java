package com.web.c101.review;

import java.time.LocalDateTime;

public class ReviewAdaptor {
    public static ReviewDto entityToDto(Review review) {
        return ReviewDto.builder()
                .mid(review.getMid())
                .store(review.getStore())
                .content(review.getContent())
                .star_score(review.getStar_score())
                .img(review.getImg())
                .flag(review.getFlag())
                .build();
    }

    public static Review dtoToEntity(ReviewDto reviewDto) {
        return Review.builder()
                .mid(reviewDto.getMid())
                .store(reviewDto.getStore())
                .content(reviewDto.getContent())
                .star_score(reviewDto.getStar_score())
                .img(reviewDto.getImg())
                .reg_date(LocalDateTime.now())
                .flag(reviewDto.getFlag())
                .build();
    }
}
