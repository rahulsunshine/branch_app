package com.ivl.suggestionsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivl.suggestionsproject.entity.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Integer>  {

}
