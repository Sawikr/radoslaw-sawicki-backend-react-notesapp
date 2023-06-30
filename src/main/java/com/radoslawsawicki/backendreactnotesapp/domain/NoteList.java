package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="NOTE_LISTS")
public class NoteList {

	private Long noteListId;
	private String listName;
	private List<Note> noteList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "NOTE_LIST_ID", unique = true)
	public Long getNoteListId() {
		return noteListId;
	}

	@NonNull
	@Column(name = "LIST_NAME")
	public String getListName() {
		return listName;
	}

	@OneToMany(
			targetEntity = Note.class,
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			mappedBy = "noteList",
			fetch = FetchType.LAZY)
	public List<Note> getNoteList() {
		return noteList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NoteList noteList1 = (NoteList) o;
		return Objects.equals(noteListId, noteList1.noteListId) && Objects.equals(listName, noteList1.listName) && Objects.equals(noteList, noteList1.noteList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(noteListId, listName, noteList);
	}
}
