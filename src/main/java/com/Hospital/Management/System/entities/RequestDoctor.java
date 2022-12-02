package com.Hospital.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
