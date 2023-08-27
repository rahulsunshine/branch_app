package com.ivl.suggestionsproject.serviceimpl;
import com.google.gson.Gson;
import com.ivl.suggestionsproject.custom.exception.EmptyInputException;
import com.ivl.suggestionsproject.dao.EventRepository;
import com.ivl.suggestionsproject.entity.EventGallery;
import com.ivl.suggestionsproject.entity.ImageModel;
import com.ivl.suggestionsproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventImpl implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Override
	public EventGallery  saveImage(String eventName, String eventDescription, MultipartFile[] file) {
		String message = "";
		if (eventName.isEmpty() || eventName.length() == 0 || eventDescription.isEmpty()
				|| eventDescription.length() == 0 || file == null) {
			throw new EmptyInputException("601", "Input fields are empty");
		}
		try {
			/*if (file != null) {
				for (int i=0; i<file.length; i++)
				{
					//file[i].getOriginalFilename();
				Set<ImageModel> imageModel = new ImageModel(file[i].getOriginalFilename(),file[i].getContentType(),file[i].getBytes());
				message = "Uploaded the file successfully:";
				EventGallery eventGallery = new EventGallery(eventName, eventDescription,new SimpleDateFormat("dd/MM/yyyy").parse(eventDate),imageModel);
				eventRepository.save(eventGallery);
				
				}
			}else {
				message="File is not avalable on given location ";
				
			}*/
			
			Set<ImageModel> eventModels = uploadImage(file);
            EventGallery eventGallery=new EventGallery(eventName,eventDescription,eventModels);
            return eventRepository.save(eventGallery);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
			return null;
			//return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
	}

	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> eventModels = new HashSet<>();
		for (MultipartFile file : multipartFiles) {
			ImageModel eventModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			eventModels.add(eventModel);
		}
		return eventModels;

	}


	@Override
	public ResponseEntity<List<EventGallery>> getEventByName(String eventName) {
		List event=eventRepository.findByeventName(eventName);
        if(event.isEmpty()) {
            throw new NoSuchElementException("No record fount by given name");
        }
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

	
	@Override
	public List<EventGallery> getAllActiveImages() {
		List event = eventRepository.findAll();
		if (event.isEmpty()) {
			throw new NoSuchElementException("No record fount on given ID");
		}
		return event;
	}

	/*
	 * @Override public List<EventGallery> getImageByDate(Date evenDate) { List
	 * event = eventRepository.findAllByDate(evenDate); if (event.isEmpty()) { throw
	 * new NoSuchElementException("No record fount by given name"); } return event;
	 * }
	 */

	@Override
	public Object store(MultipartFile[] file) {
	
		return null;
	}

	@Override
	public ResponseEntity<List<EventGallery>> findTopByOrderByIdDesc() {
		// TODO Auto-generated method stub
		List event=eventRepository.findTopByOrderByIdDesc();
		System.out.println("List===============>0"+new Gson().toJson(event));
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}

	}
