package com.project.userservice.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String email;

	private String password;

	private String fullName;

	private String mobile;

	private byte[] profileImage;

	private String dob;

	private String gender;

	private List<RoleDTO> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserDTO() {
	}

	public UserDTO(String email, String password, String fullName) {

		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
				+ ", mobile=" + mobile + ", profileImage=" + Arrays.toString(profileImage) + ", dob=" + dob
				+ ", gender=" + gender + "]";
	}
 
	
}
