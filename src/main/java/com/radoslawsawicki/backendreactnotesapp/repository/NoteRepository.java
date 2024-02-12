package com.radoslawsawicki.backendreactnotesapp.repository;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findAll(Pageable page);

    @Override
    Optional<Note> findById(Long id);

    @Override
    Note save(Note note);

    @Override
    void deleteById(Long id);
}
