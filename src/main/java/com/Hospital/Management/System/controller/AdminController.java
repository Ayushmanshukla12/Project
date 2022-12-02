package com.Hospital.Management.System.controller;
import com.Hospital.Management.System.dto.AssignDto;
import com.Hospital.Management.System.dto.DoctorDto;
import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.dto.NurseDto;
import com.Hospital.Management.System.entities.NursePojo;
import com.Hospital.Management.System.service.ImplementingClasses.AdminServiceImplClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authenticate")
public class AdminController {
    @Autowired
    private AdminServiceImplClasses adminServiceImplClasses;

    @PostMapping("/addDoctor")
    public DoctorPojo addDoctor(@RequestBody DoctorPojo doctorPojo){
        return this.adminServiceImplClasses.addDoctor(doctorPojo);
    }

    @PutMapping("/updateDoctor/{id}")
    public DoctorDto updateDoctor(@PathVariable int id, @RequestBody DoctorDto doctorDto){
        return this.adminServiceImplClasses.updateDoctor(id,doctorDto);
    }

    @DeleteMapping ("/deleteDoctor/{id}")
    public void deleteDoctor(@PathVariable int id){

         adminServiceImplClasses.deleteDoctor(id);
    }

    @GetMapping("/showDoctors")
    public List<DoctorPojo> showAllDoctors(){
        return this.adminServiceImplClasses.showAllDoctors();
    }

    @PostMapping("/addNurse")
    public NursePojo addNurse(@RequestBody NursePojo nursePojo){
        return this.adminServiceImplClasses.addNurse(nursePojo);
    }

    @PutMapping("/updateNurse/{id}")
    public NurseDto updateNurse(@PathVariable int id, @RequestBody NurseDto nurseDto){
        return this.adminServiceImplClasses.updateNurse(id,nurseDto);
    }

    @DeleteMapping ("/deleteNurse/{id}")
    public void deleteNurse(@PathVariable int id){
        adminServiceImplClasses.deleteNurse(id);
    }

    @GetMapping("/showNurses")
    public List<NursePojo> showAllNurses(){
        return this.adminServiceImplClasses.showAllNurses();
    }

    @PutMapping("/assignNurse")
    public String assignNurse(@RequestBody AssignDto assignDto){
      return  this.adminServiceImplClasses.assignNurse(assignDto);
    }

    @PutMapping("/assignDoctor")
    public String assignDoctor(@RequestBody AssignDto assignDto){
        return  this.adminServiceImplClasses.assignDoctor(assignDto);
    }
}
