package com.Hospital.Management.System.repository;

import com.Hospital.Management.System.entities.RequestDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestDoctorRepository extends JpaRepository<RequestDoctor,Integer> {

    Optional<RequestDoctor> findByrequestDoctorId(Integer integer);

//
}
