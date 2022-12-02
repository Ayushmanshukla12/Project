package com.Hospital.Management.System.util.EmailGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailBody {

    private String address;
    private String subject;
    private String body;
}
