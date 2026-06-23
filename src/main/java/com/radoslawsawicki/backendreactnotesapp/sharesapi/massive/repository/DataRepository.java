package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.repository;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.domain.SharesMassive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DataRepository extends CrudRepository<SharesMassive, Long> {

    @Override
    Optional<SharesMassive> findById(Long id);

    @Override
    SharesMassive save(SharesMassive sharesMassive);
}
