package com.web.c101.review;

import com.web.c101.BasicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@Slf4j
public class ReviewController {

    ReviewService service;

    // 리뷰 작성
    @PostMapping("/review/addReview")
    @ApiOperation(value = "리뷰등록")
    public Object addReview(@RequestBody ReviewDto req){
        log.info("리뷰 등록");
        final BasicResponse result = new BasicResponse();

        if(service.addReview(req)) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }

    // 리뷰 삭제
    @PutMapping("/review/deleteReview")
    @ApiOperation(value = "리뷰삭제")
    public Object delReview(@RequestParam String rid) {
        log.info("리뷰 삭제");
        final BasicResponse result = new BasicResponse();

        if(service.delReview(rid)){
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }

    // 사용자가 작성한 리뷰 목록
    @GetMapping("/review/userReviewList")
    @ApiOperation(value = "사용자 리뷰 목록")
    public Object getUserReview(String mid) {

        log.info("사용자 작성 리뷰 목록");
        final BasicResponse result = new BasicResponse();

        List<ReviewDto> list = service.getUserReview(mid);

        if(list != null) {

            result.status = true;
            result.data = "success";
            result.object = list;

        } else {

            result.status = false;
            result.data = "fail";

        }

        return result;
    }

    // 사용자가 작성한 리뷰 목록
    // dong이 ""라면 해당 맛집에 달린 모든 리뷰를 반환한다.
    // dong이 ""이 아니라면 dong에 해당하는 지역의 맛집 리뷰를 반환한다.
    @GetMapping("/review/storeReviewList")
    @ApiOperation(value = "지역에 따른 맛집 리뷰 목록")
    public Object getStoreReview(String dong, String store) {

        log.info("지역에 따른 맛집 리뷰 목록");
        final BasicResponse result = new BasicResponse();

        List<ReviewDto> list = service.getStoreReview(dong, store);

        if(list != null) {

            result.status = true;
            result.data = "success";
            result.object = list;

        } else {

            result.status = false;
            result.data = "fail";

        }

        return result;
    }



}
