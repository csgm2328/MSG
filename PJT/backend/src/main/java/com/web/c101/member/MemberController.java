package com.web.c101.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    private static final String SUCCESS = "success";

}
