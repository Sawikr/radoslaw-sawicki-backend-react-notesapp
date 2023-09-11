package com.radoslawsawicki.backendreactnotesapp.security.repository;

import com.radoslawsawicki.backendreactnotesapp.security.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
