package com.dhruza.creditcardapplicationapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_file")
public class ApplicationFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_status_id")
    private ApplicationStatus fileStatus;

    @ManyToOne()
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationFile that = (ApplicationFile) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "ApplicationFile{" +
                "id=" + id +
                ", locationUrl='" + locationUrl + '\'' +
                '}';
    }
}
