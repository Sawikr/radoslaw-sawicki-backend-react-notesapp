package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "LOGIN_USERS")
public class LoginUser {

    private Long loginUserId;
    private String loginName;
    private boolean isLogin;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name = "LOGIN_USER_ID", unique = true)
    public Long getLoginUserId() {
        return loginUserId;
    }

    @NonNull
    @Column(name = "LOGIN_NAME")
    public String getLoginName() {
        return loginName;
    }

    @NonNull
    @Column(name = "IS_LOGIN")
    public boolean isLogin() {
        return isLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginUser loginUser = (LoginUser) o;
        return isLogin == loginUser.isLogin && Objects.equals(loginUserId, loginUser.loginUserId) && Objects.equals(loginName, loginUser.loginName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginUserId, loginName, isLogin);
    }
}
