package com.Hospital.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestDoctorId;
    private int oldNurseId;
    private int newNurseId;
    private String requestDoctorStatus;
    @ManyToOne
    @JsonBackReference
    private DoctorPojo doctorPojo;

    @Override
    public String toString() {
        return "RequestDoctor{" +
                "requestDoctorId=" + requestDoctorId +
                ", oldNurseId=" + oldNurseId +
                ", newNurseId=" + newNurseId +
                ", requestDoctorStatus='" + requestDoctorStatus + '\'' +
                '}';
    }
}
