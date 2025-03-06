package org.example.addressbook.repositories;

import org.example.addressbook.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
        public EmployeeEntity findByEmail(String email);

    }
