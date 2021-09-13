package com.web.c101.member;

import com.web.c101.BasicResponse;
import com.web.c101.member.request.DeleteMemberRequest;
import com.web.c101.member.request.LoginRequest;
import com.web.c101.member.request.SignUpRequest;
import com.web.c101.member.request.UpdateMemberRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    /*
    @PostMapping("/member")
    public ResponseEntity<BasicResponse> signUp(SignUpRequest signUpRequest) {
        log.info("sign up request");
        MemberDto memberDto = memberService.signUp(signUpRequest);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "Success";
        result.object = memberDto;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
     */

    @GetMapping("/member")
    public ResponseEntity<BasicResponse> login(String authorizeCode) {
        log.info("login request");
        String uid = memberService.login(authorizeCode);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "Success";
        result.object = uid;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
    @PutMapping("/member")
    public ResponseEntity<BasicResponse> updateMember(UpdateMemberRequest updateMemberRequest) {
        log.info("update member request");
        MemberDto memberDto = memberService.updateMember(updateMemberRequest);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "Success";
        result.object = memberDto;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
     */

    /*
    @DeleteMapping("/member")
    public ResponseEntity<BasicResponse> deleteMember(DeleteMemberRequest deleteMemberRequest) {
        log.info("delete member request");
        MemberDto memberDto = memberService.deleteMember(deleteMemberRequest);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "Success";
        result.object = memberDto;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    */
}
