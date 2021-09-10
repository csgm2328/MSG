package com.web.c101.member;

import com.web.c101.error.CustomException;
import com.web.c101.member.kakao.KakaoService;
import com.web.c101.member.request.DeleteMemberRequest;
import com.web.c101.member.request.LoginRequest;
import com.web.c101.member.request.SignUpRequest;
import com.web.c101.member.request.UpdateMemberRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.web.c101.error.ErrorCode.MEMBER_DUPLICATE_RESOURCE;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {
    private KakaoService kakaoService;
    private MemberDao memberDao;

    //    public MemberDto signUp(SignUpRequest signUpRequest){
//        Optional<Member> chkMember = memberDao.findMemberByUserId(signUpRequest.getUid());
//        if(chkMember.isPresent()){
//            throw new CustomException(MEMBER_DUPLICATE_RESOURCE);
//        }
//
//    }
//
    public String login(String authorizeCode) {
        return kakaoService.kakaoLogin(authorizeCode);
    }
//
//    public MemberDto deleteMember(DeleteMemberRequest deleteMemberRequest){
//
//    }
//
//    public MemberDto updateMember(UpdateMemberRequest updateMemberRequest){
//
//    }

}
