package com.Hospital.Management.System.controller;

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


    @PostMapping ("/login")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getUserPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication).trim();

            String  userName= loginDto.getUserName().trim();


            if(userName.equals("admin14@gmail.com")){
                id = 1;
                role="Admin-Role";
            }
            else {
                DoctorPojo doctorPojo=doctorPojoRepository.getByDoctorPojoEmail(userName).get();
                if(doctorPojo!=null)
                {
                    id=doctorPojo.getDoctorPojoId();
                    role="Doctor_Role";
                }
                else
                {
                    NursePojo nursePojo=nursePojoRepository.getByNursePojoEmail(userName).get();
                    if(nursePojo!=null){
                        System.out.println("Is not available");
                        id=nursePojo.getNursePojoId();
                        role="Nurse_Role";
                    }
                }
            }
            System.out.println(token);
            return ResponseEntity.ok(new LoginResponseDto(token,id,role));
        }

        catch (Exception e) {
            throw new BadCredentialsException("Bad Credentials");
        }

    }
    @PostMapping("/resetpassword/{userId}")
    public String resetPassword(@PathVariable int id, @RequestBody UserPojo userPojo){
        return  null;
    }


    @GetMapping("/forgotpassword/{userName}")
    public String forgotPassword(@PathVariable String userName){
        passwordService.forgetPassword(userName);
        return "mail sent succesfully";
    }

    }

