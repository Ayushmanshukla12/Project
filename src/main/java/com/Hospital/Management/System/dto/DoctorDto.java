package com.Hospital.Management.System.dto;

import lombok.Data;

@Data
public class DoctorDto {


    private long DoctorId;
    private String userEmail;      //email
    private String doctorName;
    private String doctorSpecialization;
    private boolean isDeleted;

    public DoctorDto(String userEmail, String doctorName, String doctorSpecialization) {
        this.userEmail = userEmail;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
    }
}
