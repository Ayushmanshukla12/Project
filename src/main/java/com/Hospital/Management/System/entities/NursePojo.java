package com.Hospital.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "nursePojoEmail")})
public class NursePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nursePojoId;
    private int nursePojoYearsOfExp;
    private String nursePojoName;
    private String nursePojoEmail;
    private String nursePojoPassword;
    private String nursePojoStatus;
    private boolean nursePojoIsDeleted;
@ManyToOne
@JsonBackReference
    private DoctorPojo doctorPojo;
@OneToMany(mappedBy = "nursePojo")
@JsonManagedReference
private List<RequestNurse> requestNurses;

    @Override
    public String toString() {
        return "NursePojo{" +
                "nursePojoId=" + nursePojoId +
                ", nursePojoYearsOfExp=" + nursePojoYearsOfExp +
                ", nursePojoName='" + nursePojoName + '\'' +
                ", nursePojoEmail='" + nursePojoEmail + '\'' +
                ", nursePojoStatus='" + nursePojoStatus + '\'' +
                ", nursePojoIsDeleted=" + nursePojoIsDeleted +
                '}';
    }
}
