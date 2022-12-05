package com.Hospital.Management.System.repository;

import com.Hospital.Management.System.entities.NursePojo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface NursePojoRepository extends JpaRepository<NursePojo, Integer> {


    Optional<NursePojo> getByNursePojoEmail(String nursePojoEmail);
    List<NursePojo> findByNursePojoIsDeletedFalse();
   // List<NursePojo> findByNursePojoStatusFalse();
    List<NursePojo> findByNursePojoIsDeletedFalseAndNursePojoStatusFalse();


}
