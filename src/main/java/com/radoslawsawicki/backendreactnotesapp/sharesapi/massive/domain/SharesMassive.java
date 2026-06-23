package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SHARES_MASSIVE")
public class SharesMassive {

    private Long id;

    private List<DataMassive> data = new ArrayList<>();

    public SharesMassive(List<DataMassive> data) {
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @OneToMany(
            targetEntity = DataMassive.class,
            mappedBy = "sharesMassive",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    public List<DataMassive> getData() {
        return data;
    }

}
