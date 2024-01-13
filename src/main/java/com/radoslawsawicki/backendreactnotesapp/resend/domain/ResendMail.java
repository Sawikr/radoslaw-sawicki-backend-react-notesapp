package com.radoslawsawicki.backendreactnotesapp.resend.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="RESENDS")
public class ResendMail {

    private Long id;
    private String emailName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "EMAIL_NAME")
    public String getEmailName() {
        return emailName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResendMail that = (ResendMail) o;
        return Objects.equals(id, that.id) && Objects.equals(emailName, that.emailName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailName);
    }
}
