package com.Hospital.Management.System.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rolePojoId;
    private String rolePojoName;
    @ManyToMany
    private Set<UserPojo> userPojos;
}
