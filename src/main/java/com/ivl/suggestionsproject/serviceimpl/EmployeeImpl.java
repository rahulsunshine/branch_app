package com.ivl.suggestionsproject.serviceimpl;
import com.ivl.suggestionsproject.custom.exception.EmployeePortalListEmptyException;
import com.ivl.suggestionsproject.custom.exception.EmptyInputException;
import com.ivl.suggestionsproject.dao.UserRepository;
import com.ivl.suggestionsproject.entity.EmployeePortal;
import com.ivl.suggestionsproject.entity.ImageModel;
import com.ivl.suggestionsproject.message.ResponseMessage;
import com.ivl.suggestionsproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    UserRepository userRepository;

 

    @Override
    public ResponseEntity<ResponseMessage> save(@RequestParam("empId") String empId ,@RequestParam("empName") String empName,@RequestParam("category") String category,@RequestParam("location") String location,@RequestParam("department") String department,@RequestParam("description") String description,@RequestParam MultipartFile file) {
    	String message = "";
    	if(empName.isEmpty() || empName.length()==0
                || empId.isEmpty() || empId.length()==0
                || category.isEmpty() || category.length()==0
                || location.isEmpty() || location.length()==0
                || department.isEmpty() || department.length()==0
                || description.isEmpty() || description.length()==0) {
            throw new EmptyInputException("601","Input fields are empty");
        }
        try {
        	 
            if(file==null) {
                EmployeePortal employeePortal=new EmployeePortal(empId,empName,category,location,department,description,new java.sql.Date(System.currentTimeMillis()));
                userRepository.save(employeePortal);
                message = "Could not upload File " + file.getOriginalFilename();

                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

            }else {
                ImageModel imageModel = new ImageModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());
                EmployeePortal employeePortal=new EmployeePortal(empId,empName,category,location,department,description,new java.sql.Date(System.currentTimeMillis()),imageModel);
                 userRepository.save(employeePortal);
                 message = "Uploaded the file successfully:";
                 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body( new ResponseMessage(message));

        }
    }


//  @GetMapping("/files/{id}")
//  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//    ImageModel fileDB = storageService.getFile(id);
//
//    return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//        .body(fileDB.getData());
//  }
    
    @Override
    public ResponseEntity<EmployeePortal> getEmpById(String empId) {
        EmployeePortal employeePortal=userRepository.findByempId(empId);
        if(employeePortal!=null) {
        	return ResponseEntity.status(HttpStatus.OK).body(employeePortal);
        }
        throw new NoSuchElementException("No record fount on given ID");
    }
    @Override
    public ResponseEntity<List<EmployeePortal>>getAllEmp()
    {
        List emp = userRepository.findAll();
        if(emp.isEmpty()) {
            throw new EmployeePortalListEmptyException("No record fount in database");
        }
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @Override
    public ResponseEntity<List<EmployeePortal>> getEmpByName(String name) {
        List emp=userRepository.findByempName(name);
        if(emp.isEmpty()) {
            throw new NoSuchElementException("No record fount by given name");
        }
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @Override
    public ResponseEntity<List<EmployeePortal>> getEmpBylocation(String location) {
        List loc=userRepository.findBylocation(location);
        if(loc.isEmpty()) {
            throw new NoSuchElementException("No record fount by given name");
        }
        return ResponseEntity.status(HttpStatus.OK).body(loc);
    }

    @Override
    public ResponseEntity<List<EmployeePortal>> getEmpByDepartment(String department) {
        List dept=userRepository.findBydepartment(department);
        if(dept.isEmpty()) {
            throw new NoSuchElementException("No record fount by given Location");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dept);
    }

}
