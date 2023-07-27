package com.radoslawsawicki.backendreactnotesapp.repository;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoginUserRepository extends CrudRepository<LoginUser, Long> {

    @Override
    List<LoginUser> findAll();

    @Override
    Optional<LoginUser> findById(Long id);

    @Override
    LoginUser save(LoginUser loginUser);

    @Override
    void deleteById(Long id);
}
