package com.Hospital.Management.System.controller;

import com.Hospital.Management.System.dto.ForgotPasswordDto;
import com.Hospital.Management.System.dto.ResetPasswordDto;
import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.NursePojo;
import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.helper.LoginRequestDto;
import com.Hospital.Management.System.helper.LoginResponseDto;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.NursePojoRepository;
import com.Hospital.Management.System.security.JwtTokenProvider;
import com.Hospital.Management.System.service.ImplementingClasses.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
int id;
String role="";
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private NursePojoRepository nursePojoRepository;
    @Autowired
    private AuthenticationManager authenticationManager;//this is a inbuilt interface so you cant write @service over it so u will have to make its bean here

@CrossOrigin
    @PostMapping ("/login")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getUserPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication).trim();
            int id=jwtTokenProvider.getUserId(jwtTokenProvider.getUsernameFromJWT(token).trim());
            String role=jwtTokenProvider.getRole(jwtTokenProvider.getUsernameFromJWT(token).trim());
            System.out.println(token);
            return ResponseEntity.ok(new LoginResponseDto(token,id,role));
        }

        catch (Exception e) {
            throw new BadCredentialsException("Bad Credentials");
        }

    }
    @CrossOrigin
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){

        return  this.passwordService.resetPassword(resetPasswordDto) ;
    }


//    @GetMapping("/forgotpassword/{userName}")
//    public String forgotPassword(@PathVariable String userName){
//        passwordService.forgetPassword(userName);
//        return "mail sent succesfully";
//    }

    @CrossOrigin
    @PostMapping("/forgotPassword")
    public ResponseEntity<HashMap<ForgotPasswordDto, String>> forgetPassword(@RequestBody ForgotPasswordDto forgetPasswordDto)
    {
        return passwordService.forgetPassword(forgetPasswordDto);


    }
    }

