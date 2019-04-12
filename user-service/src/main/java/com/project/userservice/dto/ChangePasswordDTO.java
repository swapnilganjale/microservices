package com.project.userservice.dto;

import javax.validation.constraints.NotEmpty;

public class ChangePasswordDTO {
	
	@NotEmpty(message="existing password field is required")
	private String existingPassword;
	
	@NotEmpty(message="new password is missing")
	private String newPassword;

	public String getExistingPassword() {
		return existingPassword;
	}

	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	

}
