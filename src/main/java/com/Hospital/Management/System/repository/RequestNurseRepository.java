package com.Hospital.Management.System.repository;

import com.Hospital.Management.System.entities.RequestDoctor;
import com.Hospital.Management.System.entities.RequestNurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestNurseRepository extends JpaRepository<RequestNurse,Integer> {

//    List<RequestNurse> findByrequestNurseStatus();
}
