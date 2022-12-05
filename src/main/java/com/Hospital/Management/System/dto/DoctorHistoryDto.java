package com.Hospital.Management.System.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorHistoryDto {

    private int requestId;
    private int doctorId;
    private int oldNurseId;
    private int newNurseId;
    private String doctorName;
    private String oldNurseName;
    private String newNurseName;

    @Override
    public String toString() {
        return "DoctorHistoryDto{" +
                "requestId=" + requestId +
                ", doctorId=" + doctorId +
                ", oldNurseId=" + oldNurseId +
                ", newNurseId=" + newNurseId +
                ", doctorName='" + doctorName + '\'' +
                ", oldNurseName='" + oldNurseName + '\'' +
                ", newNurseName='" + newNurseName + '\'' +
                '}';
    }
}
