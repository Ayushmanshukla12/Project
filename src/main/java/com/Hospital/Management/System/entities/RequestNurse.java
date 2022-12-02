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
public class RequestNurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestNurseId;
    private int oldDoctorId;
    private int newDoctorId;
    private String requestNurseStatus;
    @ManyToOne
    @JsonBackReference
    private NursePojo nursePojo;
}
