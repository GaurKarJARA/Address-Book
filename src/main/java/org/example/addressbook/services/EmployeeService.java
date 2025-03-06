package org.example.addressbook.services;

import org.example.addressbook.dto.EmployeeDTO;
import org.example.addressbook.dto.ResponseDTO;
import org.example.addressbook.entities.EmployeeEntity;
import org.example.addressbook.interfaces.IEmployeeService;
import org.example.addressbook.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class EmployeeService implements IEmployeeService {

        @Autowired
        EmployeeRepo employeeRepo;

        public ResponseDTO response(String message, String status){
            return new ResponseDTO(message, status);
        }

        public EmployeeDTO get(Long id){
            EmployeeEntity foundEmp = EmployeeRepo.findById(id).orElseThrow(()->new RuntimeException("Cannot find employee with given id"));

            EmployeeDTO resDto = new EmployeeDTO(foundEmp.getName(), foundEmp.getEmail(), foundEmp.getId());

            return resDto;

        }

        public EmployeeDTO create(EmployeeDTO user){
            EmployeeEntity newUser = new EmployeeEntity(user.getName(), user.getEmail());

            EmployeeRepo.save(newUser);

            EmployeeDTO resDto = new EmployeeDTO(newUser.getName(), newUser.getEmail(), newUser.getId());

            System.out.println(newUser.getId());

            return resDto;
        }

        public List<EmployeeDTO> getAll(){

            return EmployeeRepo.findAll().stream().map(entity -> {
                EmployeeDTO newUser = new EmployeeDTO(entity.getName(), entity.getEmail(), entity.getId());
                return newUser;
            }).collect(Collectors.toList());

        }

        public EmployeeDTO edit(EmployeeDTO user, Long id){
            EmployeeEntity foundEmp = EmployeeRepo.findById(id).orElseThrow(()->new RuntimeException("cannot find employee with given id"));

            foundEmp.setName(user.getName());
            foundEmp.setEmail(user.getEmail());

            EmployeeRepo.save(foundEmp);

            EmployeeDTO resDto = new EmployeeDTO(foundEmp.getName(), foundEmp.getEmail(), foundEmp.getId());

            return resDto;
        }

        public String delete(Long id){
            EmployeeEntity foundUser = EmployeeRepo.findById(id).orElseThrow(()->new RuntimeException("cannot find user with given id"));

            EmployeeRepo.delete(foundUser);

            return "employee deleted";
        }

        public String clear(){

            EmployeeRepo.deleteAll();
            return "db cleared";

        }

    }
