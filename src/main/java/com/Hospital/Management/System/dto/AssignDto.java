package com.Hospital.Management.System.dto;

import lombok.Data;

@Data
public class AssignDto {

    private int doctorId;
    private int nurseId;

    @Override
    public String toString() {
        return "AssignDto{" +
                "doctorId=" + doctorId +
                ", nurseId=" + nurseId +
                '}';
    }
}
