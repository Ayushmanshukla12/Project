package com.Hospital.Management.System.util.EmailGenerator;


import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.repository.DoctorPojoRepository;
import com.Hospital.Management.System.repository.UserPojoRepository;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingClass  {
    @Autowired
    private JavaMailSender javaMailSender;
        public String sendSimpleMail (String toEmail,
                                      String body,
                                      String subject){
            try {
                // Creating a simple mail message
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                String sender = "ayushmanshukla14@gmail.com";
                mailMessage.setFrom(sender);
//                mailMessage.setTo(details.getAddress());
//                details.setBody(generateSecurePassword(userPojo.getUserName()));
//                mailMessage.setText(details.getBody());
//                mailMessage.setSubject(details.getSubject());

                mailMessage.setTo(toEmail);
                mailMessage.setText(body);
                mailMessage.setSubject(subject);

                // Sending the mail
                javaMailSender.send(mailMessage);
                return "Mail Sent Successfully...";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error while Sending Mail";
            }
        }
    }

