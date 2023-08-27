package com.ivl.suggestionsproject.entity;


import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "suggestion_user")
public class EmployeePortal {

	@Id
	@Column(name = "empId")
	private String empId;

	@Column(name="empName")
	private String empName;
	
	@Column(name="department")
	private String department;
	
	@Column(name="location")
	private String location;
	
	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;
	
	@Temporal(TemporalType.DATE)
    Date creationDate;
 
  /*  @Temporal(TemporalType.TIME)
    Date publicationTime;
 
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;*/

	@OneToOne(cascade= CascadeType.ALL)
	private ImageModel suggestionImages;

	public EmployeePortal() {
	}

	public EmployeePortal(String empId, String empName, String category,String location, String department, String description, Date creationDate) {
		this.empId = empId;
		this.empName = empName;
		this.department = department;
		this.location = location;
		this.category = category;
		this.description = description;
		this.creationDate= creationDate;
	}
	public EmployeePortal(String empId, String empName, String category,String location, String department, String description,Date creationDate, ImageModel suggestionImages) {
		this.empId = empId;
		this.empName = empName;
		this.department = department;
		this.location = location;
		this.category = category;
		this.description = description;
		this.suggestionImages = suggestionImages;
		this.creationDate = creationDate;
	}


	public ImageModel getSuggestionImages() {
		return suggestionImages;
	}

	public void setSuggestionImages(ImageModel suggestionImages) {
		this.suggestionImages = suggestionImages;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/*public Date getPublicationTime() {
		return publicationTime;
	}



	public Date getCreationDateTime() {
		return creationDateTime;
	}*/




}
