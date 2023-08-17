package com.radoslawsawicki.backendreactnotesapp.mail.repository;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.mail.domain.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {

    @Override
    List<Mail> findAll();

    @Override
    Optional<Mail> findById(Long id);

    @Override
    Mail save(Mail mail);

    @Override
    void deleteById(Long id);
}
