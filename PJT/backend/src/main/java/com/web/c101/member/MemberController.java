package com.web.c101.member;

import com.web.c101.BasicResponse;
import com.web.c101.jwt.TokenDto;
import com.web.c101.member.kakao.KakaoService;
import com.web.c101.member.request.SignUpRequest;
import com.web.c101.member.response.LoginResponse;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;
    private KakaoService kakaoService;

    @PostMapping("/signup")
    public ResponseEntity<BasicResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("sign up request");
        boolean signUpResult = memberService.signUp(signUpRequest);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "Success";
        result.object = signUpResult;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value = "일반 로그인")
    public Object login(@RequestBody String authorizeCode, HttpServletResponse response) {
        BasicResponse result = new BasicResponse();
        LoginResponse loginResponse;

        result.status = true;
        result.data = "success";

        String mid = kakaoService.kakaoLogin(authorizeCode);

        boolean isExistUser = memberService.existsUserCheck(mid);

        // 만약 존재하지 않는 유저라면 회원가입을 유도하기 위해 isMember를 false로 반환
        if (!isExistUser) {
            loginResponse = LoginResponse.builder()
                    .isMember(false)
                    .build();

            result.object = loginResponse;
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        // 현재 mid의 Member 정보를 가져와 반환 객체인 loginResponse에 필요한 내용을 담는다.
        Member member = memberService.getMemberByMid(mid);

        loginResponse = LoginResponse.builder()
                .mid(Long.toString(member.getId()))
                .nickname(member.getNickname())
                .isMember(true)
                .build();

        result.object = loginResponse;

        // 로그인을 처리하기 위한 토큰을 발급받고 쿠키에 담는다.
        Optional<TokenDto> tokenDtoOptional = memberService.login(mid, member.getNickname());

        if (tokenDtoOptional.isPresent()) {
            TokenDto token = tokenDtoOptional.get();

            Cookie cookie = new Cookie("accessToken", token.getAccessToken());
            // 일주일로 설정
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/ncheck")
    public Object nicknameDupCheck(String nickname) {
        BasicResponse result = new BasicResponse();

        result.status = true;
        result.data = "success";
        result.object = memberService.nicknameCheck(nickname);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
