package com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.repository;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.domain.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {

    @Override
    Optional<Data> findById(Long id);

    @Override
    Data save(Data data);
}
