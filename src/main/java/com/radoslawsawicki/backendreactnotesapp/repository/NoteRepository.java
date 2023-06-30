package com.radoslawsawicki.backendreactnotesapp.repository;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    @Override
    List<Note> findAll();

    @Override
    Optional<Note> findById(Long id);

    @Override
    Note save(Note note);

    @Override
    void deleteById(Long id);
}
