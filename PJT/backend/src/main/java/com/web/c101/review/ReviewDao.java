package com.web.c101.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewDao extends JpaRepository<Review, String> {

    Optional<Review> findByRid(String rid);

}
