package com.Hospital.Management.System.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String userPassword;

    public UserPojo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @ManyToMany
    @JoinTable(
            name = "User_Role",
            joinColumns = { @JoinColumn(name = "user_Id",referencedColumnName = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "role_Pojo_Id",referencedColumnName = "rolePojoId") }
    )
    private Set<RolePojo> rolePojos;
}
