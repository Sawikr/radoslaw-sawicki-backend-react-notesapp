package com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.repository;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.domain.Pagination;
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
