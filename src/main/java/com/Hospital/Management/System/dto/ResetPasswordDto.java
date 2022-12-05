package com.Hospital.Management.System.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

    private String userName;
    private String userOldPassword;
    private String userNewPassword;
}
