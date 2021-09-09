package com.web.c101.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name = "mid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(name = "uid")
    private String userId;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "flag")
    private boolean flag;

    @Column(name = "membercol")
    private String memberCol;
}
