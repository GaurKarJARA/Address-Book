package org.example.addressbook.repositories;

import org.example.addressbook.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
        public ContactEntity findByEmail(String email);

    }
