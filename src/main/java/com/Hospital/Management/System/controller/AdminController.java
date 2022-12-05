package com.Hospital.Management.System.controller;
import com.Hospital.Management.System.dto.*;
import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.NursePojo;
import com.Hospital.Management.System.entities.RequestDoctor;
import com.Hospital.Management.System.entities.RequestNurse;
import com.Hospital.Management.System.service.ImplementingClasses.AdminServiceImplClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
//@PreAuthorize("hasRole('admin')")
@RequestMapping("/authenticate")
public class AdminController {
    @Autowired
    private AdminServiceImplClasses adminServiceImplClasses;

    @PostMapping("/addDoctor")
    public ResponseEntity<HashMap<DoctorPojo,String >> addDoctor(@RequestBody DoctorPojo doctorPojo){
        return this.adminServiceImplClasses.addDoctor(doctorPojo);
    }
    @CrossOrigin
    @PutMapping("/updateDoctor/{id}")
    public ResponseEntity<HashMap<DoctorDto,String>> updateDoctor(@PathVariable int id, @RequestBody DoctorDto doctorDto){
        return this.adminServiceImplClasses.updateDoctor(id,doctorDto);
    }

    @CrossOrigin
    @DeleteMapping ("/deleteDoctor/{doctorId}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("doctorId") int doctorId){
        try {
            this.adminServiceImplClasses.deleteDoctor(doctorId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/showDoctors")
    public List<DoctorPojo> showAllDoctors(){
        return this.adminServiceImplClasses.showAllDoctors();
    }

    @PostMapping("/addNurse")
    public ResponseEntity<HashMap<NursePojo,String>> addNurse(@RequestBody NursePojo nursePojo){
        return this.adminServiceImplClasses.addNurse(nursePojo);
    }

    @CrossOrigin
    @PutMapping("/updateNurse/{id}")
    public ResponseEntity<HashMap<NursePojo,String>> updateNurse(@PathVariable int id, @RequestBody NurseDto nurseDto){
        return this.adminServiceImplClasses.updateNurse(id,nurseDto);
    }

    @CrossOrigin
    @DeleteMapping ("/deleteNurse/{id}")
    public ResponseEntity<HttpStatus> deleteNurse(@PathVariable("id") int nurseId){
        try {
            this.adminServiceImplClasses.deleteNurse(nurseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/showNurses")
    public List<NursePojo> showAllNurses(){
        return this.adminServiceImplClasses.showAllNurses();
    }

    @GetMapping("/showUnAllocatedNurses")
    public List<NursePojo> showUnAllocatedNurses(){
        return this.adminServiceImplClasses.showUnAllocatedNurses();
    }

    @CrossOrigin
    @PutMapping("/assignNurse")
    public String assignNurse(@RequestBody AssignDto assignDto){
      return  this.adminServiceImplClasses.assignNurse(assignDto);
    }

    @PutMapping("/assignDoctor")
    public String assignDoctor(@RequestBody AssignDto assignDto){
        return  this.adminServiceImplClasses.assignDoctor(assignDto);
    }

    @PostMapping("/doctorRequestStatus")
    public String doctorReplaceRequest(@RequestBody RequestDto requestDto){
        return this.adminServiceImplClasses.doctorRequestStatus(requestDto);
    }

    @PostMapping("/nurseRequestStatus")
    public String nurseReplaceRequest(@RequestBody RequestDto requestDto){
        return this.adminServiceImplClasses.nurseRequestStatus(requestDto);
    }
    @GetMapping("/showDoctorHistory")
    public List<RequestDoctor> showDoctorHistory(){
        return this.adminServiceImplClasses.showDoctorHistory();
    }

    @GetMapping("/showNurseHistory")
    public List<RequestNurse> showNurseHistory(){
        return this.adminServiceImplClasses.showNurseHistory();
    }
    @GetMapping("/showDoctorPendingRequests")
    public List<RequestDoctor> showDoctorPendingRequests(){
        return this.adminServiceImplClasses.shownDoctorPendingRequests();
    }

    @GetMapping("/showNursePendingRequests")
    public List<RequestNurse> showNursePendingRequests(){
        return this.adminServiceImplClasses.shownNursePendingRequests();
    }
    @GetMapping("/showAllAssignedNurses/{doctorId}")
    public List<NursePojo> showAllAssignedNurses(@PathVariable int doctorId){
        return  this.adminServiceImplClasses.allAssignedNurses(doctorId);
    }
}
