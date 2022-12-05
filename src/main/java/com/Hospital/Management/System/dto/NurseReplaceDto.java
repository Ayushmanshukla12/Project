package com.Hospital.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NurseReplaceDto {

    private int nurseId;
    private int oldDoctorId;
    private int newDoctorId;
}
