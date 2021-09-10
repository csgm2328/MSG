package com.web.c101.review;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.web.c101.member.Member;
import com.web.c101.member.MemberDao;


@Service
@AllArgsConstructor
public class ReviewService {

    ReviewDao reviewdao;
    MemberDao memberdao;

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

        Optional<Review> reviewOpt = reviewdao.findReviewByRid(rid);

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

    public List<ReviewDto> getReview(String uid) {

        Optional<Member> member = memberdao.findMemberByUserId(uid);
        List<ReviewDto> list = null;

        if(member.isPresent()) {

            List<Review> reviewList = reviewdao.findReviewByUid(uid);
            ReviewDto tmp;

            for(Review R : reviewList) {
               tmp = ReviewAdaptor.entityToDto(R);
               list.add(tmp);
            }

        }

        return list;
    }
}
