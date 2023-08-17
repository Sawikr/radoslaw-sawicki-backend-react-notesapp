package com.radoslawsawicki.backendreactnotesapp.mail.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="MAILS")
public class Mail {

    private Long id;
    private String email;
    private String title;
    private String body;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return Objects.equals(id, mail.id) && Objects.equals(email, mail.email) && Objects.equals(title, mail.title) && Objects.equals(body, mail.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, title, body);
    }
}
