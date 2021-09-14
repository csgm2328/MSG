package com.web.c101.review;

import com.web.c101.error.CustomException;
import com.web.c101.error.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public boolean delReview(long rid) {

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

    public List<ReviewDto> getUserReview(long mid) {

        Optional<Member> member = memberdao.findMemberByMemberId(mid);
        List<ReviewDto> list = null;

        if(member.isPresent()) {

            List<Review> reviewList = reviewdao.findReviewByMid(mid);
            ReviewDto tmp;
            list = new ArrayList<>();

            for(Review R : reviewList) {
               tmp = ReviewAdaptor.entityToDto(R);
               list.add(tmp);
            }

        }

        return list;
    }

    public List<ReviewDto> getStoreReview(String dong, String store) {

        List<ReviewDto> list = null;

        try{

            List<Review> reviewList;

            if(dong == null){
                reviewList = reviewdao.findReviewByStore(store);
            } else {
                reviewList = reviewdao.findReviewByDongAndStore(dong, store);
            }

            ReviewDto tmp;
            list = new ArrayList<>();

            for(Review R  : reviewList) {
                tmp = ReviewAdaptor.entityToDto(R);
                list.add(tmp);
            }

        } catch (Exception e) {
            throw new CustomException(ErrorCode.KAKAO_LOGIN_EXCEPTION);
        }

        return list;
    }

}
