package com.ivl.suggestionsproject.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ivl.suggestionsproject.entity.EmployeePortal;
import com.ivl.suggestionsproject.entity.EventGallery;
import com.ivl.suggestionsproject.message.ResponseMessage;
import com.ivl.suggestionsproject.service.EmployeeService;
import com.ivl.suggestionsproject.service.EventService;
import com.ivl.suggestionsproject.service.ImageService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EventService eventService;
    
    @Autowired
   ImageService imageService;

    @PostMapping(value = {"/save"} , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseMessage> saveSuggessionDetails(@RequestParam("empId") String empId ,@RequestParam("empName") String empName,@RequestParam("category") String category,@RequestParam("location") String location,@RequestParam("department") String department,@RequestParam("description") String description,@RequestParam("file") MultipartFile file) {
     
    	return employeeService.save(empId,empName,category,location,department,description,file);
    		
    }

    
    @GetMapping("/getSuggessionDataById/{empId}")
    public ResponseEntity<EmployeePortal> getSuggessionDataById(@PathVariable String empId) {
        return employeeService.getEmpById(empId);
    }

    @GetMapping("/getusers")
    private ResponseEntity<List<EmployeePortal>> getSuggessionDetailsByusers() {
        return  employeeService.getAllEmp();
    }

    @GetMapping("/name/{empName}")
    public ResponseEntity<List<EmployeePortal>> getSuggessionDetailsByempName(@PathVariable String empName)
    { 
    	return employeeService.getEmpByName(empName);
    	}

    @GetMapping("/location/{location}")
    public ResponseEntity<List<EmployeePortal>> getSuggessionDetailsByLocation(@PathVariable String location) {
        return  employeeService.getEmpBylocation(location);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<EmployeePortal>> getSuggessionDetailsByDepartment(@PathVariable String department) {
        return employeeService.getEmpByDepartment(department);
    }
    
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable int id) {
      return imageService.getFile(id);
    }


    @PostMapping("/saveEventDetails")
    public EventGallery saveEventDetails(@RequestParam ("eventName") String eventName, @RequestParam ("eventDescription") String eventDescription
                                      ,@RequestParam("image") MultipartFile[] file)  {
//    	System.out.println("eventDate");
        return eventService.saveImage(eventName, eventDescription, file);
    }

    @GetMapping("/getEventByName/{eventName}")
    public ResponseEntity<List<EventGallery>> getEventByName(@PathVariable String eventName)
    {
    	return eventService.getEventByName(eventName);
    	}

    
    
    
	/*
	 * @GetMapping("/image/display/{date}") public List<EventGallery>
	 * showImageByDate(@PathVariable("date") Date evenDate) throws ServletException,
	 * IOException { return eventService.getImageByDate(evenDate); }
	 */

    @GetMapping("/image/displayAllEventDetails")
    public List<EventGallery> showAllImage()  {
        return eventService.getAllActiveImages();
    }
    @GetMapping("/getLatestEventDeatils")
    public ResponseEntity<List<EventGallery>> getLatestEventDeatils() {
        return eventService.findTopByOrderByIdDesc();
    }
    
    
}
