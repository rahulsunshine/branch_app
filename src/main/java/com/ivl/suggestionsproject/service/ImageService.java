package com.ivl.suggestionsproject.service;

import org.springframework.http.ResponseEntity;

public interface ImageService {
	ResponseEntity<byte[]> getFile( int id);
}
