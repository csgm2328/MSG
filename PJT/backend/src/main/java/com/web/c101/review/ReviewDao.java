package com.web.c101.review;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReviewDao extends JpaRepository<Review, String> {

    Optional<Review> findReviewByRid(String rid);

    List<Review> findReviewByUid(String uid);

}
