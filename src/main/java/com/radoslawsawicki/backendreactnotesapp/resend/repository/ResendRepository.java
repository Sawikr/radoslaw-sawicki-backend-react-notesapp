package com.radoslawsawicki.backendreactnotesapp.resend.repository;

import com.radoslawsawicki.backendreactnotesapp.resend.domain.ResendMail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResendRepository extends CrudRepository<ResendMail, Long> {

    @Override
    List<ResendMail> findAll();
}
