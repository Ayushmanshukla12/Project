package com.Hospital.Management.System.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    private int userId;
    private String userRole;
    private String tokenType = "Bearer";

    public LoginResponseDto(String obtainedToken,int userId,String userRole)
    {
        this.token=obtainedToken;
        this.userId=userId;
        this.userRole=userRole;
    }
}
