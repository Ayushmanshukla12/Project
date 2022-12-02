package com.Hospital.Management.System.repository;

import com.Hospital.Management.System.entities.DoctorPojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorPojoRepository extends JpaRepository<DoctorPojo,Integer> {

    Optional<DoctorPojo> getByDoctorPojoEmail(String doctorPojoEmail);
}
