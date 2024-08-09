package com.jsp.Thymleaf_CRUD.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Student
{
	@Id
	@GeneratedValue(generator = "x")
	@SequenceGenerator(initialValue = 1001 , allocationSize = 1, name = "x")
	private int id;
	
	private String name;
	private	long mobile;
	private String gender;
	private int math;
	private int science;
	private int english;
	
	public double getPercentage()
	{
		return (math + science + english) / 3.0;
	}
	
}
