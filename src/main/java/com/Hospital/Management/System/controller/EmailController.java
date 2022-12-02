package com.Hospital.Management.System.controller;

import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.UserPojoRepository;
import com.Hospital.Management.System.util.EmailGenerator.EmailBody;


import com.Hospital.Management.System.util.EmailGenerator.EmailSendingClass;
import com.Hospital.Management.System.util.EmailGenerator.MailInterface;
import com.Hospital.Management.System.util.EmailGenerator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class EmailController {

    @Autowired
    private EmailSendingClass emailService;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private UserPojoRepository userPojoRepository;

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody DoctorPojo doctorPojo)

    {
        String password=passwordGenerator.generateSecurePassword(doctorPojo.getDoctorPojoEmail());
        String status = emailService.sendSimpleMail(doctorPojo.getDoctorPojoEmail(),password,doctorPojo.getDoctorPojoSpecialization());
        UserPojo userPojo = new UserPojo(doctorPojo.getDoctorPojoEmail(),password);


        doctorPojoRepository.save(doctorPojo);
        userPojoRepository.save(userPojo);


        return status;
    }
}
