package com.project.userservice.dto;

import java.io.Serializable;

public class RoleDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public RoleDTO(Integer id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}

}
