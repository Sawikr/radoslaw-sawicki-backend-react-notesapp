package com.radoslawsawicki.backendreactnotesapp.security.mapper;

import com.radoslawsawicki.backendreactnotesapp.security.domain.User;
import com.radoslawsawicki.backendreactnotesapp.security.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserMapper {

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
<<<<<<< HEAD
                user.getUsername(),
                user.getEmail()
=======
            user.getUsername(),
            user.getEmail()
>>>>>>> 89451b7 (Notes: adding UserDto, UserMapper, UserService and UserController)
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> user) {
        return user.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
