package com.Hospital.Management.System.service.ImplementingClasses;

import com.Hospital.Management.System.dto.DoctorReplaceDto;
import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.NursePojo;
import com.Hospital.Management.System.entities.RequestDoctor;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.NursePojoRepository;
import com.Hospital.Management.System.repository.RequestDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceClassImpl {
    @Autowired
    private NursePojoRepository nursePojoRepository;
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private RequestDoctorRepository repository;
    public String doctorReplaceRequest(DoctorReplaceDto doctorReplaceDto) {

        int newNurseId=doctorReplaceDto.getNewNurseId();
        System.out.println(newNurseId);
        DoctorPojo doctorPojo = doctorPojoRepository.getById(doctorReplaceDto.getDoctorId());
        //NursePojo nursePojo = nursePojoRepository.getOne(doctorReplaceDto.getOldNurseId());
        NursePojo nursePojo1 = nursePojoRepository.getById(doctorReplaceDto.getNewNurseId());

        if (!nursePojo1.isNursePojoStatus() && !nursePojo1.isNursePojoIsDeleted()) {
            System.out.println("++++++++++++");
            RequestDoctor requestDoctor = new RequestDoctor();
            requestDoctor.setRequestDoctorId(doctorReplaceDto.getDoctorId());
            requestDoctor.setDoctorPojo(doctorPojo);
            requestDoctor.setNewNurseId(doctorReplaceDto.getNewNurseId());
            requestDoctor.setOldNurseId(doctorReplaceDto.getOldNurseId());
            requestDoctor.setRequestDoctorStatus("pending");
            repository.save(requestDoctor);

            return "nurse replace request created ";
        }else {
            System.out.println();
            return "nurse is already allocated to someone else";
        }
    }
}
