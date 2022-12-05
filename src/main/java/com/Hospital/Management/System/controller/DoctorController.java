package com.Hospital.Management.System.controller;

import com.Hospital.Management.System.dto.DoctorReplaceDto;
import com.Hospital.Management.System.service.ImplementingClasses.DoctorServiceClassImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class DoctorController {
    @Autowired
    private DoctorServiceClassImpl doctorServiceClass;

    @PostMapping("/doctorReplaceRequest")
    public String doctorReplaceRequest(@RequestBody DoctorReplaceDto doctorReplaceDto){
        return this.doctorServiceClass.doctorReplaceRequest(doctorReplaceDto);
    }

//    public String showAllRequests(){
//
//    }
}


