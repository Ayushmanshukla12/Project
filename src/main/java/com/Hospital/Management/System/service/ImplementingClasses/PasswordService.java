package com.Hospital.Management.System.service.ImplementingClasses;

import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.UserPojoRepository;
import com.Hospital.Management.System.util.EmailGenerator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private UserPojoRepository userPojoRepository;
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private PasswordGenerator passwordGenerator;

//    public String restPassword(ResetPasswordDto resetPasswordDto){
//        UserPojo userPojo = userPojoRepository.findByUserName(resetPasswordDto.getUserName()).get();
//        if(userPojo!=null){
//            if(userPojo.getUserPassword().equals(resetPasswordDto.getUserPassword())){
//
//            }
//        }
//
//    }

//    public String resetPassword(String email, String newPassWord,String oldPassWord) {
//        UserPojo user=userPojoRepository.findByUserName(email).get();
//        if(user!=null){
//
//            if( new BCryptPasswordEncoder().matches(oldPassWord,user.getUserPassword())){
//
//                user.setUserPassword(PasswordEncoderProvider.passwordEncoder(newPassWord));
//                userPojoRepository.save(user);
//                System.out.println("done");
//                return "reset is done";
//            }else{
//                System.out.println("no");
//                return "old password doesn't match";
//
//            }
//        }
//        System.out.println("nnijiin");
//        return null;

    public void forgetPassword(String userName){
        DoctorPojo doctorPojo = doctorPojoRepository.getByDoctorPojoEmail(userName).get();
        UserPojo userPojo=userPojoRepository.findByUserName(userName).get();
        String password=passwordGenerator.generateSecurePassword(userName);
        doctorPojo.setDoctorPojoPassword(password);
        doctorPojoRepository.save(doctorPojo);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userPojo.setUserPassword(passwordEncoder.encode(password));
        userPojoRepository.save(userPojo);

    }

}
