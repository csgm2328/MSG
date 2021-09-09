package com.web.c101.review;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    private String mid;

    // 리뷰 작성한 맛집과 리뷰 내용
    private String store;
    private String content;

    // 별점
    private float star_score;
    // 리뷰 이미지
    private String img;

    // 리뷰 삭제 확인 변수
    private boolean flag;

    public boolean getFlag() {
        return this.flag;
    }

}