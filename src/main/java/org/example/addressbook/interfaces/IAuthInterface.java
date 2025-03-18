package org.example.addressbook.interfaces;

import org.example.addressbook.dto.AuthUserDTO;
import org.example.addressbook.dto.LoginDTO;
import org.example.addressbook.dto.PassDTO;
import org.springframework.stereotype.Service;

@Service
public interface IAuthInterface {

    public String register(AuthUserDTO user) ;

    public String login(LoginDTO user);

    public AuthUserDTO forgotPassword(PassDTO pass, String email) ;

    public String resetPassword(String email, String currentPass, String newPass);

    public String clear();
}