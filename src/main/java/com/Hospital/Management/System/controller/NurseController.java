package com.Hospital.Management.System.controller;

import com.Hospital.Management.System.dto.NurseReplaceDto;
import com.Hospital.Management.System.service.ImplementingClasses.NurseServiceClassImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class NurseController {
    @Autowired
    private NurseServiceClassImpl nurseServiceClass;

    @PostMapping("/nurseReplaceRequest")
    public String nurseReplaceRequest(@RequestBody NurseReplaceDto nurseReplaceDto){
        return this.nurseServiceClass.nurseReplaceRequest(nurseReplaceDto);
    }
}
