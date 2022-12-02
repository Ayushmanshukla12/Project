package com.Hospital.Management.System.service.ImplementingClasses;

import com.Hospital.Management.System.dto.AssignDto;
import com.Hospital.Management.System.dto.DoctorDto;
import com.Hospital.Management.System.dto.NurseDto;
import com.Hospital.Management.System.entities.*;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.NursePojoRepository;
import com.Hospital.Management.System.repository.RoleRepository;
import com.Hospital.Management.System.repository.UserPojoRepository;
import com.Hospital.Management.System.service.ImplementinfInterfaces.AdminServiceInterface;
import com.Hospital.Management.System.util.EmailGenerator.EmailSendingClass;
import com.Hospital.Management.System.util.EmailGenerator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImplClasses implements AdminServiceInterface {
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private NursePojoRepository nursePojoRepository;
    @Autowired
    private UserPojoRepository userPojoRepository;
    @Autowired
    private EmailSendingClass emailSendingClass;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public DoctorPojo addDoctor(DoctorPojo doctorPojo) {
        String uName = doctorPojo.getDoctorPojoEmail().trim();
        String password = passwordGenerator.generateSecurePassword(uName).trim();
        doctorPojo.setDoctorPojoPassword(password);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPassword = passwordEncoder.encode(password).trim();
        UserPojo userPojo = new UserPojo(uName, newPassword);

        RolePojo role = roleRepository.findByrolePojoName("doctor").get();
        Set<RolePojo> doctorSet = new HashSet<>();
        doctorSet.add(role);
        doctorPojoRepository.save(doctorPojo);
        userPojoRepository.save(userPojo);
        return doctorPojo;
    }

    @Override
    public DoctorDto updateDoctor(int id, DoctorDto doctorDto) {
        DoctorPojo doctorPojo = doctorPojoRepository.getById(id);
        //doctorPojo.setDoctorPojoEmail(doctorDto.getUserEmail());
        doctorPojo.setDoctorPojoSpecialization(doctorDto.getDoctorSpecialization());
        doctorPojo.setDoctorPojoName(doctorDto.getDoctorName());
        doctorPojoRepository.save(doctorPojo);
        return doctorDto;
    }

    @Override
    public void deleteDoctor(int id) {
        DoctorPojo doctorPojo = doctorPojoRepository.getById(id);
        doctorPojo.setDoctorPojoIsDeleted(true);
        doctorPojoRepository.save(doctorPojo);

    }

    @Override
    public List<DoctorPojo> showAllDoctors() {
        return doctorPojoRepository.findAll();
    }

    public NursePojo addNurse(NursePojo nursePojo) {
        String uName = nursePojo.getNursePojoEmail().trim();
        String password = passwordGenerator.generateSecurePassword(uName).trim();
        nursePojo.setNursePojoPassword(password);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPassword = passwordEncoder.encode(password).trim();
        UserPojo userPojo = new UserPojo(uName, newPassword);
        RolePojo role = roleRepository.findByrolePojoName("nurse").get();
        Set<RolePojo> nurseSet = new HashSet<>();
        nurseSet.add(role);
        nursePojoRepository.save(nursePojo);
        userPojoRepository.save(userPojo);
        return nursePojo;
    }

    @Override
    public NurseDto updateNurse(int id, NurseDto nurseDto) {
        NursePojo nursePojo = nursePojoRepository.getById(id);
        nursePojo.setNursePojoName(nurseDto.getNurseName());
        nursePojoRepository.save(nursePojo);
        return nurseDto;
    }

    @Override
    public void deleteNurse(int id) {
        NursePojo nursePojo = nursePojoRepository.getById(id);
        nursePojo.setNursePojoIsDeleted(true);
        nursePojo.setNursePojoStatus("unallocated");
        nursePojoRepository.save(nursePojo);
    }

    @Override
    public List<NursePojo> showAllNurses() {
        return nursePojoRepository.findAll();
    }


    public String assignNurse(AssignDto assignDto) {
        DoctorPojo doctorPojo = doctorPojoRepository.getById(assignDto.getDoctorId());
        NursePojo nursePojo = nursePojoRepository.getById(assignDto.getNurseId());
        if (nursePojo.isNursePojoIsDeleted() == false) {

            if (nursePojo.getNursePojoStatus().equals("unAllocated")) {

                List<NursePojo> nursePojos = doctorPojo.getNursePojos();
                nursePojos.add(nursePojo);
                doctorPojo.setNursePojos(nursePojos);
                doctorPojoRepository.save(doctorPojo);
                nursePojo.setNursePojoStatus("allocated");
                nursePojo.setDoctorPojo(doctorPojo);
                nursePojoRepository.save(nursePojo);
            } else {
                return "nurse Already allocated to anotherDoctor";
            }
            return "nurse added";
        } else {
            return "nurse is already deleted";
        }
    }
    public String assignDoctor(AssignDto assignDto) {
        NursePojo nursePojo = nursePojoRepository.getById(assignDto.getNurseId());
        DoctorPojo doctorPojo = doctorPojoRepository.getById(assignDto.getDoctorId());

        if (doctorPojo.isDoctorPojoIsDeleted() == false) {
            nursePojo.setDoctorPojo(doctorPojo);
            nursePojoRepository.save(nursePojo);

            return "doctor added";
        }else {
            return "doctor is deleted from the database";
        }
    }
}

