package com.Hospital.Management.System.service.ImplementingClasses;

import com.Hospital.Management.System.dto.ForgotPasswordDto;
import com.Hospital.Management.System.dto.ResetPasswordDto;
import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.NursePojo;
import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.NursePojoRepository;
import com.Hospital.Management.System.repository.UserPojoRepository;
import com.Hospital.Management.System.security.JwtTokenProvider;
import com.Hospital.Management.System.util.EmailGenerator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PasswordService {
    @Autowired
    private UserPojoRepository userPojoRepository;
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private NursePojoRepository nursePojoRepository;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String resetPassword(ResetPasswordDto resetPasswordDto) {

        UserPojo userPojo = userPojoRepository.findByUserName(resetPasswordDto.getUserName()).get();
        //DoctorPojo doctorPojo = doctorPojoRepository.getByDoctorPojoEmail(resetPasswordDto.getUserName()).get();
        if(userPojo!=null){
            if(new BCryptPasswordEncoder().matches(resetPasswordDto.getUserOldPassword(),userPojo.getUserPassword())){
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                userPojo.setUserPassword((passwordEncoder.encode(resetPasswordDto.getUserNewPassword())));
                userPojoRepository.save(userPojo);
                return "reset password is done successfully";
            }else {
                return "old password doesnt match";
            }
        }
        return "incorrect username entered";
    }

    public ResponseEntity<HashMap<ForgotPasswordDto, String>> forgetPassword(ForgotPasswordDto forgetPasswordDto) {

        UserPojo user=userPojoRepository.findByUserName(forgetPasswordDto.getUserName()).get();
        HashMap<ForgotPasswordDto,String> map=new HashMap<>();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(user!=null) {

            String password = passwordGenerator.generateSecurePassword(forgetPasswordDto.getUserName());
            String role=jwtTokenProvider.getRole(user.getUserName());
            if(role.equals("doctor"))
            {
                DoctorPojo doctor=doctorPojoRepository.getByDoctorPojoEmail(user.getUserName()).get();
                doctor.setDoctorPojoPassword(password);
                doctorPojoRepository.save(doctor);
                String newPass = passwordEncoder.encode(password);
                user.setUserPassword(newPass);
                userPojoRepository.save(user);

            }
            else if (role.equals("nurse")) {
                NursePojo nurse = nursePojoRepository.getByNursePojoEmail(user.getUserName()).get();
                nurse.setNursePojoPassword(password);
                nursePojoRepository.save(nurse);
                String newPass = passwordEncoder.encode(password);
                user.setUserPassword(newPass);
                userPojoRepository.save(user);

            }
            map.put(new ForgotPasswordDto(),"Forgot password worked");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
        else {
            map.put(new ForgotPasswordDto(),"Username doesnt exists ");
            return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
        }

    }
}
