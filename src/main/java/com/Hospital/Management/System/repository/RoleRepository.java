package com.Hospital.Management.System.repository;

import com.Hospital.Management.System.entities.RolePojo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RolePojo, Integer> {

    Optional<RolePojo> findByrolePojoName(String roleName);
}
