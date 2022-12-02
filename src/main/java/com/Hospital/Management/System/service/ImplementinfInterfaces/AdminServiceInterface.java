package com.Hospital.Management.System.service.ImplementinfInterfaces;

import com.Hospital.Management.System.dto.DoctorDto;
import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.dto.NurseDto;
import com.Hospital.Management.System.entities.NursePojo;

import java.util.List;

public interface AdminServiceInterface {

public DoctorPojo addDoctor(DoctorPojo doctorPojo);
public DoctorDto updateDoctor(int id, DoctorDto doctorDto);
public void deleteDoctor(int id);
public List<DoctorPojo> showAllDoctors();
public NursePojo addNurse(NursePojo nursePojo);
public NurseDto updateNurse(int id, NurseDto nurseDto);
public void deleteNurse(int id);
public List<NursePojo> showAllNurses();

}
