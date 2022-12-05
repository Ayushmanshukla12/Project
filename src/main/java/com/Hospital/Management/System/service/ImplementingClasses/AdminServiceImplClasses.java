package com.Hospital.Management.System.service.ImplementingClasses;

import com.Hospital.Management.System.dto.*;
import com.Hospital.Management.System.entities.*;
import com.Hospital.Management.System.repository.*;
import com.Hospital.Management.System.util.EmailGenerator.EmailSendingClass;
import com.Hospital.Management.System.util.EmailGenerator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminServiceImplClasses  {
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
    @Autowired
    private RequestDoctorRepository requestDoctorRepository;
    @Autowired
    private RequestNurseRepository requestNurseRepository;


    public ResponseEntity<HashMap<DoctorPojo,String>> addDoctor(DoctorPojo doctorPojo) {

        HashMap<DoctorPojo,String> hashMap = new HashMap<>();
        List<UserPojo> list = userPojoRepository.findAll();
        String uName = doctorPojo.getDoctorPojoEmail().trim();
        if (list.stream().filter(s -> s.getUserName().equals(uName)).collect(Collectors.toList()).size() == 0) {

            String password = passwordGenerator.generateSecurePassword(uName).trim();
            doctorPojo.setDoctorPojoPassword(password);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String newPassword = passwordEncoder.encode(password).trim();
            UserPojo userPojo = new UserPojo(uName, newPassword);

            RolePojo role = roleRepository.findByrolePojoName("doctor").get();
            Set<RolePojo> doctorSet = new HashSet<>();
            doctorSet.add(role);
            userPojo.setRolePojos(doctorSet);
            doctorPojoRepository.save(doctorPojo);
            userPojoRepository.save(userPojo);
            hashMap.put(new DoctorPojo(),"doctor is added");
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        }else {
            hashMap.put(new DoctorPojo(),"doctor already exists ");
            return new ResponseEntity<>(hashMap, HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<HashMap<DoctorDto,String>> updateDoctor(int id, DoctorDto doctorDto) {

        HashMap<DoctorDto,String> hashMap = new HashMap<>();
        DoctorPojo doctorPojo = doctorPojoRepository.getById(id);
        //doctorPojo.setDoctorPojoEmail(doctorDto.getUserEmail());
        doctorPojo.setDoctorPojoSpecialization(doctorDto.getDoctorSpecialization());
        doctorPojo.setDoctorPojoName(doctorDto.getDoctorName());
        doctorPojoRepository.save(doctorPojo);
        hashMap.put(new DoctorDto(),"doctor details are updated");
        return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
    }

    public void deleteDoctor(int id) {
        DoctorPojo doctorPojo = doctorPojoRepository.getById(id);
        doctorPojo.setDoctorPojoIsDeleted(true);
        doctorPojoRepository.save(doctorPojo);

    }


    public List<DoctorPojo> showAllDoctors() {
        return doctorPojoRepository.findByDoctorPojoIsDeletedFalse();
    }

    public ResponseEntity<HashMap<NursePojo,String >> addNurse(NursePojo nursePojo) {

        HashMap<NursePojo, String> hashMap = new HashMap<>();
        List<UserPojo> list = userPojoRepository.findAll();
        String uName = nursePojo.getNursePojoEmail().trim();

        if (list.stream().filter(s -> s.getUserName().equals(uName)).collect(Collectors.toList()).size() == 0) {
            String password = passwordGenerator.generateSecurePassword(uName).trim();
            nursePojo.setNursePojoPassword(password);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String newPassword = passwordEncoder.encode(password).trim();
            UserPojo userPojo = new UserPojo(uName, newPassword);

            RolePojo role = roleRepository.findByrolePojoName("nurse").get();
            Set<RolePojo> nurseSet = new HashSet<>();
            nurseSet.add(role);
            userPojo.setRolePojos(nurseSet);
            nursePojoRepository.save(nursePojo);
            userPojoRepository.save(userPojo);
            hashMap.put(new NursePojo(),"nurse is added");
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        }else {
            hashMap.put(new NursePojo(),"nurse already exists ");
            return new ResponseEntity<>(hashMap, HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<HashMap<NursePojo,String>> updateNurse(int id, NurseDto nurseDto) {

        HashMap<NursePojo, String> hashMap = new HashMap<>();
        NursePojo nursePojo = nursePojoRepository.getById(id);
        nursePojo.setNursePojoName(nurseDto.getNurseName());
        nursePojoRepository.save(nursePojo);
        hashMap.put(new NursePojo(),"nurse details are updated");
        return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
    }


    public void deleteNurse(int id) {
        NursePojo nursePojo = nursePojoRepository.getById(id);
        nursePojo.setNursePojoIsDeleted(true);
        nursePojo.setNursePojoStatus(false);
        nursePojoRepository.save(nursePojo);
    }


    public List<NursePojo> showAllNurses() {
        return nursePojoRepository.findByNursePojoIsDeletedFalse();
    }

    public List<NursePojo> showUnAllocatedNurses() {
        return nursePojoRepository.findByNursePojoIsDeletedFalseAndNursePojoStatusFalse();
    }

    public String assignNurse(AssignDto assignDto) {
        DoctorPojo doctorPojo = doctorPojoRepository.getById(assignDto.getDoctorId());
        NursePojo nursePojo = nursePojoRepository.getById(assignDto.getNurseId());
        if (nursePojo.isNursePojoIsDeleted() == false) {

            if (!nursePojo.isNursePojoStatus()) {

                List<NursePojo> nursePojos = doctorPojo.getNursePojos();
                nursePojos.add(nursePojo);
                doctorPojo.setNursePojos(nursePojos);
                doctorPojoRepository.save(doctorPojo);
                nursePojo.setNursePojoStatus(true);
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

        if (!doctorPojo.isDoctorPojoIsDeleted()) {
            if (!nursePojo.isNursePojoStatus()) {
                nursePojo.setDoctorPojo(doctorPojo);
                nursePojoRepository.save(nursePojo);
                return "doctor added";
            }else {
                return "nurse has already a doctor assigned to it";
            }
        }
        else
        {
            return "doctor is deleted from the database";
        }
    }
    public String doctorRequestStatus(RequestDto requestDto){

        RequestDoctor requestDoctor = requestDoctorRepository.getById(requestDto.getRequestIdByDoctor());

        String  status=requestDto.getRequestStatus();
        DoctorPojo doctorPojo= doctorPojoRepository.getById(requestDoctor.getDoctorPojo().getDoctorPojoId());
        NursePojo nursePojo=nursePojoRepository.findById(requestDoctor.getOldNurseId()).get();
        NursePojo nursePojo1=nursePojoRepository.findById(requestDoctor.getNewNurseId()).get();
        if(status.equals("approved")){
            doctorPojo.getNursePojos().remove(nursePojo);
            List<NursePojo> nursePojos = doctorPojo.getNursePojos();
            nursePojos.add(nursePojo1);
            doctorPojo.setNursePojos(nursePojos);
            doctorPojoRepository.save(doctorPojo);
            requestDoctor.setRequestDoctorStatus("approved");
            requestDoctorRepository.save(requestDoctor);
            nursePojo1.setNursePojoStatus(true);
            nursePojo.setNursePojoStatus(false);
            nursePojo.setDoctorPojo(null);
            nursePojo1.setDoctorPojo(doctorPojo);
            nursePojoRepository.save(nursePojo);
            return "doctor replace request approved";
        }
        else
        {
            requestDoctor.setRequestDoctorStatus("rejected");
            requestDoctorRepository.save(requestDoctor);
            return "doctor replace request rejected";
        }
    }
    public String nurseRequestStatus(RequestDto requestDto){

        RequestNurse requestNurse = requestNurseRepository.getById(requestDto.getRequestIdByNurse());
        String status = requestDto.getRequestStatus();
        NursePojo nursePojo=nursePojoRepository.findById(requestDto.getRequestIdByNurse()).get();
        DoctorPojo doctorPojo=doctorPojoRepository.findById(requestNurse.getOldDoctorId()).get();
        DoctorPojo doctorPojo1=doctorPojoRepository.findById(requestNurse.getNewDoctorId()).get();
        if(status.equals("approved")){
            nursePojo.setDoctorPojo(doctorPojo1);
            nursePojoRepository.save(nursePojo);
            requestNurse.setRequestNurseStatus("approved");
            requestNurseRepository.save(requestNurse);
            return "nurse replace request approved";
        }
        else {
            requestNurse.setRequestNurseStatus("rejected");
            requestNurseRepository.save(requestNurse);
            return "nurse replace request rejected";
        }
    }

    public List<RequestDoctor> showDoctorHistory(){
        return requestDoctorRepository.findAll();
    }

    public List<RequestNurse> showNurseHistory(){
        return  requestNurseRepository.findAll();
    }

    public List<RequestDoctor> shownDoctorPendingRequests() {
        List<RequestDoctor>  requestDoctorList=requestDoctorRepository.findAll();
        List<RequestDoctor> list=requestDoctorList.stream().filter(r->r.getRequestDoctorStatus().equals("pending"))
                .collect(Collectors.toList());
        return list;
    }
    public List<RequestNurse> shownNursePendingRequests() {
        List<RequestNurse>  requestNurseList=requestNurseRepository.findAll();
        List<RequestNurse> list=requestNurseList.stream().filter(r->r.getRequestNurseStatus().equals("pending")).collect(Collectors.toList());
        return list;
    }

    public List<NursePojo> allAssignedNurses(int doctorId){
          DoctorPojo doctorPojo = doctorPojoRepository.findById(doctorId).get();
        List<NursePojo> list=doctorPojo.getNursePojos();
        return list;
    }

    public Page
}

