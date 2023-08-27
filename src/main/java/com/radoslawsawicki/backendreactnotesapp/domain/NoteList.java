package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NOTE_LISTS")
public class NoteList {

	private Long noteListId;
	private String listName;

	public NoteList(String listName) {
		this.listName = listName;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "NOTE_LIST_ID", unique = true)
	public Long getNoteListId() {
		return noteListId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NoteList noteList = (NoteList) o;
		return Objects.equals(noteListId, noteList.noteListId) && Objects.equals(listName, noteList.listName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(noteListId, listName);
	}
}
