package com.Hospital.Management.System.service.ImplementingClasses;

import com.Hospital.Management.System.dto.DoctorReplaceDto;
import com.Hospital.Management.System.dto.NurseReplaceDto;
import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.NursePojo;
import com.Hospital.Management.System.entities.RequestDoctor;
import com.Hospital.Management.System.entities.RequestNurse;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.NursePojoRepository;
import com.Hospital.Management.System.repository.RequestDoctorRepository;
import com.Hospital.Management.System.repository.RequestNurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseServiceClassImpl {
    @Autowired
    private NursePojoRepository nursePojoRepository;
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private RequestNurseRepository requestNurseRepository;

    public String nurseReplaceRequest(NurseReplaceDto nurseReplaceDto) {
        NursePojo nursePojo = nursePojoRepository.getById(nurseReplaceDto.getNurseId());
        //NursePojo nursePojo = nursePojoRepository.getOne(doctorReplaceDto.getOldNurseId());
        DoctorPojo doctorPojo = doctorPojoRepository.getById(nurseReplaceDto.getNewDoctorId());

        if (!doctorPojo.isDoctorPojoIsDeleted()) {

            RequestNurse requestNurse = new RequestNurse();
            requestNurse.setRequestNurseId(nurseReplaceDto.getNurseId());
            requestNurse.setNursePojo(nursePojo);
            requestNurse.setNewDoctorId(nurseReplaceDto.getNewDoctorId());
            requestNurse.setOldDoctorId(nurseReplaceDto.getOldDoctorId());
            requestNurse.setRequestNurseStatus("pending");
            requestNurseRepository.save(requestNurse);
            return "doctor replace request created ";
        } else {
            System.out.println();
            return "the required doctor is deleted doctor is deleted";
        }
    }
}

