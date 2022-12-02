package com.Hospital.Management.System.service.ImplementingClasses;

import com.Hospital.Management.System.entities.RolePojo;
import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.repository.UserPojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@Configuration
public class CustomeUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserPojoRepository userPojoRepository;

    public CustomeUserDetailServiceImpl(UserPojoRepository userPojoRepository) {

        this.userPojoRepository = userPojoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserPojo userPojo = userPojoRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username or email"+username));

        return  new org.springframework.security.core.userdetails.User(userPojo.getUserName(),userPojo.getUserPassword(),
                mapRolesToAuthorities(userPojo.getRolePojos()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<RolePojo> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRolePojoName())).collect(Collectors.toList());
    }

    }

