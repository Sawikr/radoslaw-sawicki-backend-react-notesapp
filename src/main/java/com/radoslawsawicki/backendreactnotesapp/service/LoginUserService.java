package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final LoginUserRepository repository;

    public LoginUser saveLoginUser(final LoginUser loginUser) {
        return repository.save(loginUser);
    }

    public void deleteLoginUser(final Long id) {
        repository.deleteById(id);
    }
}
