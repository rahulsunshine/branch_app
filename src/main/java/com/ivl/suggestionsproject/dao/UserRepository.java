package com.ivl.suggestionsproject.dao;



import com.ivl.suggestionsproject.entity.EmployeePortal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<EmployeePortal, String>{

    List<EmployeePortal> findByempName(String eventName);
    List<EmployeePortal> findBylocation(String location);
    EmployeePortal findByempId(String empId);
    List<EmployeePortal> findBydepartment(String department);


    
    
  //  @Query("SELECT u FROM User u WHERE u.email = ?1")
  //  public User findByEmail(String email);
     
}