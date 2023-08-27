package com.ivl.suggestionsproject.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ivl.suggestionsproject.dao.ImageRepository;
import com.ivl.suggestionsproject.entity.ImageModel;
import com.ivl.suggestionsproject.service.ImageService;
@Service
public class ImageImplementation implements ImageService {
	  @Autowired
	  private ImageRepository ImageRepo;
	@Override
	public ResponseEntity<byte[]> getFile(int id) {
	
	
		ImageModel Img=ImageRepo.getById(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + Img.getName() + "\"")
	        .body(Img.getPicByte());
	}

}
