package com.ivl.suggestionsproject.dao;
import com.ivl.suggestionsproject.entity.EventGallery;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<EventGallery,Long> {
	
	List<EventGallery> findByeventName(String eventName);
	
	public List<EventGallery> findTopByOrderByIdDesc();
	
   // List<EventGallery> findBydate(Date eventDate);
	
	//List<EventGallery> findAllByDate(Date evenDate);
}
