package com.Hospital.Management.System.dto;

import lombok.*;

@Data
@AllArgsConstructor@NoArgsConstructor
@Getter
@Setter
public class NurseDto {

    private long nurseId;
    private int nurseNumberOfYears;
    private String userEmail;
    private String nurseName;
    private boolean isAllocated;
    private boolean isDeleted;

}
