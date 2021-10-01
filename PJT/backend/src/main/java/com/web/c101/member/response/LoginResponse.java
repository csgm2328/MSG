package com.web.c101.member.response;

import com.web.c101.jwt.TokenDto;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    String mid;
    String nickname;
    boolean isMember;
}
