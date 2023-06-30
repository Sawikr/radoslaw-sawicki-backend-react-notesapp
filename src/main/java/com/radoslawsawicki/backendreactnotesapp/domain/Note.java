package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NOTES")
public class Note {

	private Long noteId;
	private String title;
	private String body;
	private String category;
	private NoteList noteList;
	private LoginUser loginUser;

	public Note(String title, String body, String category, NoteList noteList) {
		this.title = title;
		this.body = body;
		this.category = category;
		this.noteList = noteList;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@NonNull
	@Column(name = "NOTE_ID", unique = true)
	public Long getNoteId() {
		return noteId;
	}

	@NonNull
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	@NonNull
	@Column(name = "BODY")
	public String getBody() {
		return body;
	}

	@NonNull
	@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "LOGIN_USER_ID")
	public LoginUser getLoginUser() {
		return loginUser;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "NOTE_LIST_ID")
	public NoteList getNoteList() {
		return noteList;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Note note = (Note) o;
		return Objects.equals(noteId, note.noteId) && Objects.equals(title, note.title) && Objects.equals(body, note.body) && Objects.equals(category, note.category) && Objects.equals(noteList, note.noteList) && Objects.equals(loginUser, note.loginUser);
	}

	@Override
	public int hashCode() {
		return Objects.hash(noteId, title, body, category, noteList, loginUser);
	}
}
