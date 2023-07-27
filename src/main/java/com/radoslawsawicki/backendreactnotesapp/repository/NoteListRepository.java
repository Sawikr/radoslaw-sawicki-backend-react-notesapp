package com.radoslawsawicki.backendreactnotesapp.repository;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteListRepository extends CrudRepository<NoteList, Long> {

    @Override
    List<NoteList> findAll();

    @Override
    Optional<NoteList> findById(Long id);

    @Override
    NoteList save(NoteList noteList);

    @Override
    void deleteById(Long id);
}
