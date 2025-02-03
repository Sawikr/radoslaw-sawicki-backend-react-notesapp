package com.radoslawsawicki.backendreactnotesapp.sharesapi.repository;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.domain.Shares;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SharesRepository extends CrudRepository<Shares, Long> {

    @Override
    List<Shares> findAll();

    @Override
    Optional<Shares> findById(Long id);

    @Override
    Shares save(Shares shares);

    @Override
    void deleteById(Long id);
}
