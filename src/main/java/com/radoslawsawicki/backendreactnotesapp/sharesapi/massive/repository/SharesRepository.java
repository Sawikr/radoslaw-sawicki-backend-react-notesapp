package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.repository;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.domain.SharesMassive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SharesRepository extends CrudRepository<SharesMassive, Long> {

    @Override
    List<SharesMassive> findAll();

    @Override
    Optional<SharesMassive> findById(Long id);

    @Override
    SharesMassive save(SharesMassive shares);

    @Override
    void deleteById(Long id);
}
