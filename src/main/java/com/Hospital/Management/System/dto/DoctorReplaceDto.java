package com.Hospital.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReplaceDto {

    private int doctorId;
    private int oldNurseId;
    private int newNurseId;
}
