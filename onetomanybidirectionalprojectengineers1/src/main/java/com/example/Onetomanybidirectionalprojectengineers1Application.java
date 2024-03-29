package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Onetomanybidirectionalprojectengineers1Application {

	public static void main(String[] args) {
		SpringApplication.run(Onetomanybidirectionalprojectengineers1Application.class, args);
	}

}
//Required for Post and Put Method in Postman and Swagger
//JSON Format for Posting a new Project in /saveProj @PostMapping
/*
 * { "name": "OS", "engineers": [ { "name": "Shrestha"
 * 
 * }, { "name": "Stuti"
 * 
 * } ] }
 */
//JSON Format for Updating a new Project in /saveProj/{projectId} @PutMapping
/*
 * { "name": "Java" }
 */
//JSON Format for Posting a new Engineer in /saveEng @PostMapping
/*
 * { "name": "Sreeja", "project": { "id": 1 } }
 */
//JSON Format for Updating a new Project in /saveEng/{engineerId} @PutMapping
/*
 * { "name":"Ishani" }
 */