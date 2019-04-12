package com.project.task1service.dto;

public class Task1DTO {
	
	private Integer id;
	private String name;
	private String status;
	private String percentage;
	private String createdAt;
	private String finishAt;

	public Task1DTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getFinishAt() {
		return finishAt;
	}

	public void setFinishAt(String finishAt) {
		this.finishAt = finishAt;
	}

}
