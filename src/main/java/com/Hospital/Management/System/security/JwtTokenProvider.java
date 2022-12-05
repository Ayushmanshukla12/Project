package com.Hospital.Management.System.security;

import com.Hospital.Management.System.entities.DoctorPojo;
import com.Hospital.Management.System.entities.NursePojo;
import com.Hospital.Management.System.entities.RolePojo;
import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.exception.BlogApiException;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.NursePojoRepository;
import com.Hospital.Management.System.repository.RoleRepository;
import com.Hospital.Management.System.repository.UserPojoRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class JwtTokenProvider {
    @Autowired
    private DoctorPojoRepository doctorPojoRepository;
    @Autowired
    private NursePojoRepository nursePojoRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserPojoRepository userPojoRepository;

    @Value("${app.jwt-secret}")
    private String jwtSecretKey;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    // generate token method

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecretKey)
                .compact();

        return token;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

     //Validate jwt token
    public boolean validateToken(String token)  {
        try{
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Invalid Jwt Signature");
        } catch (MalformedJwtException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Invalid Jwt token");
        } catch (ExpiredJwtException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
        } catch (UnsupportedJwtException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"JWT claims string is empty");
        }

    }
    public String getRole(String username){
        UserPojo user=userPojoRepository.findByUserName(username).get();
        Set<RolePojo> roleSet= user.getRolePojos();
        String role = null;
        for (RolePojo r :
                roleSet) {
            role=r.getRolePojoName();
            break;
        }
        return role;
    }



    public int getUserId(String username){
        String roleOfUser=getRole(username);
        if(roleOfUser.equals("admin"))
        {
            return 1;
        } else if (roleOfUser.equals("doctor"))
        {
            DoctorPojo doctor =doctorPojoRepository.getByDoctorPojoEmail(username).get();
            return doctor.getDoctorPojoId();
        }else
        {
            NursePojo nurse=nursePojoRepository.getByNursePojoEmail(username).get();
            return nurse.getNursePojoId();
        }

    }
}
