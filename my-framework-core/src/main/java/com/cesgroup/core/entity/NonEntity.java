package com.cesgroup.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NonEntity implements BaseEntity<String>{
	
	private static final long serialVersionUID = 1722495377602510091L;
	
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {
	}
	
	
}
