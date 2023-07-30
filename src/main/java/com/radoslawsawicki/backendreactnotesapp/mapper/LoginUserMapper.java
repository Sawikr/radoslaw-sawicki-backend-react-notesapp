package com.radoslawsawicki.backendreactnotesapp.mapper;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.dto.LoginUserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoginUserMapper {

    public LoginUser mapToLoginUser(final LoginUserDto loginUserDto) {
        return new LoginUser(
                loginUserDto.getLoginName(),
                loginUserDto.isLogin()
        );
    }

    public LoginUserDto mapToLoginUserDto(final LoginUser loginUser) {
        return new LoginUserDto(
                loginUser.getLoginUserId(),
                loginUser.getLoginName(),
                loginUser.isLogin()
        );
    }

    public List<LoginUserDto> mapToNoteListDtoList(final List<LoginUser> loginUser) {
        return loginUser.stream()
                .map(this::mapToLoginUserDto)
                .toList();
    }
}
