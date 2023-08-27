package com.ivl.suggestionsproject.service;

import com.ivl.suggestionsproject.entity.EmployeePortal;
import com.ivl.suggestionsproject.message.ResponseMessage;

import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<ResponseMessage> save(String empId,String empName,String category,String location,String department,String description, MultipartFile file);
    
    ResponseEntity<EmployeePortal> getEmpById(String empId);

    ResponseEntity<List<EmployeePortal>> getAllEmp();

    ResponseEntity<List<EmployeePortal>> getEmpByName(String name);

    ResponseEntity<List<EmployeePortal>> getEmpBylocation(String location);

    ResponseEntity<List<EmployeePortal>> getEmpByDepartment(String department);
}
