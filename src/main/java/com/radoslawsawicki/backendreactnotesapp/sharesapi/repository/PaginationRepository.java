package com.radoslawsawicki.backendreactnotesapp.sharesapi.repository;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.domain.Pagination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaginationRepository extends CrudRepository<Pagination, Long> {

    @Override
    Optional<Pagination> findById(Long id);

    @Override
    Pagination save(Pagination pagination);
}
