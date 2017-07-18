package com.randomAlco.jee;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Alcohol{
	@Id
	public ObjectId _id;
	@Field
	public String name;
	@Field
	public String details;
	@Field
	public float percent;
	@Field
	public String location; //possibly a Geolocation, by the end of this, and link to googleMap.
	@Field
	public String type;
	@Field
	public float num;
	
	public Alcohol(){
		
	}
	
	public Alcohol(String name, String details, int percent, String location){
		this.name = name;
		this.details = details;
		this.percent = percent;
		this.location = location;
	}
	
	public Alcohol(String beerName, String details, int percent, String location, String type){
		this.name = name;
		this.details = details;
		this.percent = percent;
		this.location = location;
		this.type = type;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDetails(){
		return this.details;
	}
	
	public float getPercent(){
		return this.percent;
	}
	
	public String getLocation(){
		return this.location;
	}
}
