package com.Hospital.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestNurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestNurseId;
    private int oldDoctorId;
    private int newDoctorId;
    private String  requestNurseStatus;
    private String replaceReason;
    @ManyToOne
    @JsonBackReference
    private NursePojo nursePojo;

    @Override
    public String toString() {
        return "RequestNurse{" +
                "requestNurseId=" + requestNurseId +
                ", oldDoctorId=" + oldDoctorId +
                ", newDoctorId=" + newDoctorId +
                ", requestNurseStatus='" + requestNurseStatus + '\'' +
                ", nursePojo=" + nursePojo +
                '}';
    }
}
