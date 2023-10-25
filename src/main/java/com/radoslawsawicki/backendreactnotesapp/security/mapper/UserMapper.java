package com.radoslawsawicki.backendreactnotesapp.security.mapper;

import com.radoslawsawicki.backendreactnotesapp.security.domain.User;
import com.radoslawsawicki.backendreactnotesapp.security.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserMapper {

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> user) {
        return user.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
