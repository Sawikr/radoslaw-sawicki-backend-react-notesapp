package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
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
	private Date createdAt;
	private Date updatedAt;
	private NoteCreatedDate NoteCreatedDateList;

	public Note(String title, String body, String category, Date updatedAt, NoteList noteList, LoginUser loginUser) {
		this.title = title;
		this.body = body;
		this.category = category;
		this.updatedAt = updatedAt;
		this.noteList = noteList;
		this.loginUser = loginUser;
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
	public Date getCreatedAt() {
		return createdAt;
	}

	@NonNull
	@Column(name = "UPDATED_AT")
	@CreationTimestamp
	public Date getUpdatedAt() {
		return updatedAt;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name= "LOGIN_USER_ID")
	public LoginUser getLoginUser() {
		return loginUser;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NOTE_LIST_ID")
	public NoteList getNoteList() {
		return noteList;}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NOTE_CREATED_DATE_LIST_ID")
	public NoteCreatedDate getNoteCreatedDateList() {
		return NoteCreatedDateList;
	}

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
