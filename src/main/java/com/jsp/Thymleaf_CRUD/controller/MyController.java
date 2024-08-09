package com.jsp.Thymleaf_CRUD.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsp.Thymleaf_CRUD.dto.Student;
import com.jsp.Thymleaf_CRUD.repository.StudentRespository;


@Controller
public class MyController
{
	@Autowired
	StudentRespository studentRespository;
	
	@GetMapping("/")
	public String loadMain(ModelMap map)
	{
		List<Student> students= studentRespository.findAll();
		if(students.isEmpty())
		{
			map.put("failure", "Data is Empty no Record found");
			return "main.html";
		}
		else
		{
			
			map.put("list", students);
			return "main.html";
		}
	}
	
	@GetMapping("/insert")
	public String loadInsert()
	{
		return "insert.html";
	}
	
	@PostMapping("/insert")
	public String insert(Student student,ModelMap map)
	{
		studentRespository.save(student);
		map.put("success", "Data save success");
		return loadMain(map);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, ModelMap map)
	{
		studentRespository.deleteById(id);
		map.put("success", "Data deleted successfully");
		return loadMain(map);
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, ModelMap map)
	{
		Student student = studentRespository.findById(id).orElseThrow();
		map.put("student",student);
		return "edit.html" ;
	}
	@PostMapping("/edit")
	public String update(Student student, ModelMap map)
	{
		studentRespository.save(student);
		return loadMain(map) ;
	}
	
	
	@GetMapping("/fetch")
	public String fetchData(ModelMap map)
	{
		map.put("fetch", "fetch");
		return loadMain(map);
	}
	@GetMapping("/fetch/{field}")
	public String fetchparticular(@PathVariable String field , ModelMap map)
	{
		map.put("field", field);
		return loadMain(map);
	}
	
	@PostMapping("/fetch/name")
	public String fetchByName(@RequestParam String name, ModelMap map)
	{
		List<Student> student=studentRespository.findByName(name);
		if(student.isEmpty())
		{
			map.put("failure", "Data is Empty no Record found");
			return "main.html";
		}
		else
		{
			
			map.put("list", student);
			map.put("success", "Record found");
			return "main.html";
		}
	}
		
		@PostMapping("/fetch/mobile")
		public String fetchByMobile(@RequestParam long mobile, ModelMap map)
		{
			List<Student> student=studentRespository.findByMobile(mobile);
			if(student.isEmpty())
			{
				map.put("failure", "Data is Empty no Record found");
				return "main.html";
			}
			else
			{
				
				map.put("list", student);
				map.put("success", "Record found");
				return "main.html";
			}
		
		}
		
		@PostMapping("/fetch/gender")
		public String fetchByGender(@RequestParam String gender, ModelMap map)
		{
			List<Student> student=studentRespository.findByGender(gender);
			if(student.isEmpty())
			{
				map.put("failure", "Data is Empty no Record found");
				return "main.html";
			}
			else
			{
				
				map.put("list", student);
				map.put("success", "Record found");
				return "main.html";
			}
		
		}
		@PostMapping("/fetch/marks")
		public String fetchName(@RequestParam int marks, ModelMap map)
		{
			List<Student> student=studentRespository.findByMathGreaterThanAndScienceGreaterThanAndEnglishGreaterThan(marks,marks,marks);
			if(student.isEmpty())
			{
				map.put("failure", "Data is Empty no Record found");
				return "main.html";
			}
			else
			{
				
				map.put("list", student);
				map.put("success", "Record found");
				return "main.html";
			}
		
		}
		
		@PostMapping("/fetch/percentage")
		public String fetchByPercentage(@RequestParam int percentage, ModelMap map)
		{
			List<Student> students = studentRespository.findAll().stream().filter(student -> student.getPercentage() >= percentage)
					.collect(Collectors.toList());		
			if(students.isEmpty())
			{
				map.put("failure", "Data is Empty no Record found");
				return "main.html";
			}
			else
			{
				
				map.put("list", students);
				map.put("success", "Record found");
				return "main.html";
			}
		
		}
		@GetMapping("/login")
		public String login(ModelMap map)
		{
			map.put("msg", "Welcome to login page");
			return "login.html";
		}
		
		@PostMapping("/login")
		public String login(@RequestParam String name , @RequestParam long mobile ,ModelMap map)
		{
			List<Student> students=studentRespository.findByNameOrMobile(name,mobile);
			boolean flag=false;
			for(Student std:students)
			{
				if(std.getName().equals(name) && std.getMobile()==mobile)
				{
					flag=true;
					break;
				}
				
			}
			if(flag==true)
			{
				map.put("success", "login sucess");
				return loadMain(map);
			}
			else {
				map.put("failure", "Enter the valid data");
				return "login.html";
			}
		}



	
}
