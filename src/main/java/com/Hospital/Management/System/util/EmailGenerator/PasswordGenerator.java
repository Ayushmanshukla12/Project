package com.Hospital.Management.System.util.EmailGenerator;

import lombok.AllArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {
@Autowired
    private EmailSendingClass emailSendingClass;

    public String generateSecurePassword(String userName) {
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        // set number of lower case characters
        LCR.setNumberOfCharacters(4);
        // create character rule for upper case
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        // set number of upper case characters
        UCR.setNumberOfCharacters(4);
        // create character rule for digit
        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        // set number of digits
        DR.setNumberOfCharacters(4);
        org.passay.PasswordGenerator passGen = new org.passay.PasswordGenerator();
        // call generatePassword() method of PasswordGenerator class to get Passay generated password
        String password = passGen.generatePassword(12, LCR, UCR, DR);
       emailSendingClass.sendSimpleMail(userName,password,"regarding new password generation");
        return password ;
    }
}
