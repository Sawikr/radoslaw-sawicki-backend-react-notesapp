package com.radoslawsawicki.backendreactnotesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserDto {

    private Long loginUserId;
    private String loginName;
    private boolean isLogin;
}
