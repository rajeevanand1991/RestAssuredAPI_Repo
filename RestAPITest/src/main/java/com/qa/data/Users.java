package com.qa.data;

//POJO class - Plain Old Java Objects
public class Users {
	String name, job,id,createdAt;
	
	//getters and setters
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	public Users(String name,String job) {
		this.name = name;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}	
	
}
