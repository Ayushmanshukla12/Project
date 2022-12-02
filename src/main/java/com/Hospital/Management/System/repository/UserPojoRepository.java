package com.Hospital.Management.System.repository;

import com.Hospital.Management.System.entities.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPojoRepository extends JpaRepository<UserPojo,Integer> {

    //Optional<UserPojo> findByEmail(String email);
    Optional<UserPojo> findByUserName(String userName);
//    Boolean existsByUserName(String username);
//    Boolean existsByEmail(String email);
}
