package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NOTE_CREATED_DATES")
public class NoteCreatedDate {

    private Long id;
    private Date noteCreationDate;
    private List<Note> noteCreatedDateList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @CreationTimestamp
    @Column(name = "CREATED_AT")
    public Date getNoteCreationDate() {
        return noteCreationDate;
    }

    @OneToMany(
            targetEntity = Note.class,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "noteCreatedDateList",
            fetch = FetchType.LAZY)
    public List<Note> getNoteCreatedDateList() {
        return noteCreatedDateList;
    }
}
