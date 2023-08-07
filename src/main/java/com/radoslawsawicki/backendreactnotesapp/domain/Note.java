package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NOTES")
public class Note {

	private Long id;
	private String title;
	private String body;
	private String category;
	private NoteList noteList;
	private LoginUser loginUser;
	private LocalDate createdAt;
	private LocalDate updatedAt;

	public Note(Long id, String title, String body, String category, LocalDate createdAt, LocalDate updatedAt) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.category = category;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID", unique = true)
	public Long getId() {
		return id;
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

	@NonNull
	@Column(name = "CREATED_AT")
	@CreationTimestamp
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	@NonNull
	@Column(name = "UPDATED_AT")
	@CreationTimestamp
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "LOGIN_USER_ID")
	public LoginUser getLoginUser() {
		return loginUser;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NOTE_LIST_ID")
	public NoteList getNoteList() {
		return noteList;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Note note = (Note) o;
		return Objects.equals(id, note.id) && Objects.equals(title, note.title) && Objects.equals(body, note.body) && Objects.equals(category, note.category) && Objects.equals(noteList, note.noteList) && Objects.equals(loginUser, note.loginUser) && Objects.equals(createdAt, note.createdAt) && Objects.equals(updatedAt, note.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, body, category, noteList, loginUser, createdAt, updatedAt);
	}
}
