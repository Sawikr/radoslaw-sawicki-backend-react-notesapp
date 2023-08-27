package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.exception.LoginUserNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final LoginUserRepository repository;

    public List<LoginUser> getAllLoginUsers() {
        return repository.findAll();
    }

    public LoginUser getLoginUser(final Long loginUserId) throws LoginUserNotFoundException {
        return repository.findById(loginUserId).orElseThrow(LoginUserNotFoundException::new);
    }

    public LoginUser getLoginUser() throws LoginUserNotFoundException {
        return repository.findById(repository.findAll().stream().findFirst().orElseThrow().getLoginUserId()).orElseThrow(LoginUserNotFoundException::new);
    }

    public LoginUser saveLoginUser(final LoginUser loginUser) {
        return repository.save(loginUser);
    }

    public void deleteLoginUser(final Long id) {
        repository.deleteById(id);
    }
}
