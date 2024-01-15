package com.radoslawsawicki.backendreactnotesapp.security.repository;

import com.radoslawsawicki.backendreactnotesapp.security.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);
}
