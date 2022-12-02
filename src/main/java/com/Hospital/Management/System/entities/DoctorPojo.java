package com.Hospital.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "doctorPojoEmail")})
public class DoctorPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorPojoId;
    private String doctorPojoName;
    private String doctorPojoPassword;
    private String doctorPojoEmail;
    private String doctorPojoSpecialization;
    private boolean doctorPojoIsDeleted;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctorPojo")
    private List<NursePojo> nursePojos;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctorPojo")
    private List<RequestDoctor> requestDoctors;

    @Override
    public String toString() {
        return "DoctorPojo{" +
                "doctorPojoId=" + doctorPojoId +
                ", doctorPojoName='" + doctorPojoName + '\'' +
                ", doctorPojoEmail='" + doctorPojoEmail + '\'' +
                ", doctorPojoSpecialization='" + doctorPojoSpecialization + '\'' +
                ", doctorPojoIsDeleted=" + doctorPojoIsDeleted +
                '}';
    }
}

