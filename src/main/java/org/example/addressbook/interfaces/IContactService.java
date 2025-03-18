package org.example.addressbook.interfaces;

import java.util.*;
import org.example.addressbook.dto.ContactDTO;
import org.example.addressbook.dto.ResponseDTO;
import org.springframework.stereotype.Service;

    @Service
    public interface IContactService {

        public ContactDTO get(Long id) ;

        public ContactDTO create(ContactDTO user) ;

        public String clear();

        public List<ContactDTO> getAll();

        public ContactDTO edit(ContactDTO user, Long id) ;

        public String delete(Long id);

        public ResponseDTO response(String message, String status);
    }

