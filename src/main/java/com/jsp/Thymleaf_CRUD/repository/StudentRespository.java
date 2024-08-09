package com.jsp.Thymleaf_CRUD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Thymleaf_CRUD.dto.Student;

public interface StudentRespository extends JpaRepository<Student, Integer>{

	List<Student> findByName(String name);

	List<Student> findByMobile(long mobile);

	List<Student> findByGender(String gender);

	List<Student> findByMathGreaterThanAndScienceGreaterThanAndEnglishGreaterThan(int marks, int marks2, int marks3);
	
	List<Student> findByNameOrMobile(String name, long mobile);

}
