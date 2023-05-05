package com.dhruza.creditcardapplicationapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String oib;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private ApplicationStatus status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Set<ApplicationFile> applicationFiles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        return getOib() != null ? getOib().equals(applicant.getOib()) : applicant.getOib() == null;
    }

    @Override
    public int hashCode() {
        return getOib() != null ? getOib().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", oib='" + oib + '\'' +
                '}';
    }
}