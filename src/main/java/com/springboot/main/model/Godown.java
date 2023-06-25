package com.springboot.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Godown {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String location; 
	
	private double capacityInQuintals; 
	
	@OneToOne
	private Manager manager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCapacityInQuintals() {
		return capacityInQuintals;
	}

	public void setCapacityInQuintals(double capacityInQuintals) {
		this.capacityInQuintals = capacityInQuintals;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	} 
	
	
}
