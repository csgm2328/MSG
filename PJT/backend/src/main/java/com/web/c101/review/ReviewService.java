package com.web.c101.review;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    ReviewDao reviewdao;

    public boolean addReview(ReviewDto req) {

        Review review = ReviewAdaptor.dtoToEntity(req);

        try{
            reviewdao.save(review);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean delReview(String rid) {

        Optional<Review> reviewOpt = reviewdao.findByRid(rid);

        if(reviewOpt.isPresent()) {

            // rid에 해당하는 review를 찾고 그 flag값을 false로 변경한다.
            Review review = reviewOpt.get();
            review.setFlag(false);

            reviewdao.save(review);

            return true;
        } else {
            return false;
        }

    }
}
