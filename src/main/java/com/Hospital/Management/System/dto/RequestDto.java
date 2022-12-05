package com.Hospital.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private int requestIdByDoctor;
    private int requestIdByNurse;
    private String requestStatus;

}
