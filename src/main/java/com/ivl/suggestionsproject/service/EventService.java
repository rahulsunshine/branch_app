package com.ivl.suggestionsproject.service;
import com.ivl.suggestionsproject.entity.EventGallery;
import com.ivl.suggestionsproject.message.ResponseMessage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;



public interface EventService {
    public EventGallery saveImage(String eventName, String eventDescription,MultipartFile[] file);

    ResponseEntity<List<EventGallery>> getEventByName(String eventName);
    
    @Async
    @Query("SELECT * FROM event_gallery t1 order by id desc limit 2") 
    ResponseEntity<List<EventGallery>> findTopByOrderByIdDesc();
    
    public List<EventGallery> getAllActiveImages();

    //public List<EventGallery> getImageByDate(Date evenDate);

	public Object store(MultipartFile[] file);

}
