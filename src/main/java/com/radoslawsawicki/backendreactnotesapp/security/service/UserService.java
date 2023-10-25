package com.radoslawsawicki.backendreactnotesapp.security.service;

import com.radoslawsawicki.backendreactnotesapp.security.domain.User;
import com.radoslawsawicki.backendreactnotesapp.security.exception.UserNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() {
        return (List<User>) repository.findAll();
    }

    public User getUser(final Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
