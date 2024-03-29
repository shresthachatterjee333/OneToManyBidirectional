package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Engineer;
import com.example.entity.Project;
import com.example.repository.EngineerJPA;

@RestController
public class EngineerController {
	@Autowired
	private EngineerJPA engineerRepository;

	@GetMapping("/saveEng/{id}")
	public ResponseEntity<String> getEngineerById(@PathVariable int id) {
		try {
			Optional<Engineer> engineerOptional = engineerRepository.findById(id);
			if (engineerOptional.isPresent()) {
				Engineer engineer = engineerOptional.get();
				// Get the associated project
				Project project = engineer.getProject();
				String response = "Engineer Details:\n" + engineer.toString();
				if (project != null) {
					response += "\nProject Name: " + project.getName() + " " + project.getId();
				}
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Engineer not found!", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/saveEng")
	public ResponseEntity<String> saveEngineer(@RequestBody Engineer engineer) {
		try {
			if (engineer == null) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(engineerRepository.save(engineer) + " saved!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/saveEng/{engineerId}")
	public String updateEngineer(@PathVariable int engineerId, @RequestBody Engineer engineerDetails) {
		Optional<Engineer> optionalEngineer = engineerRepository.findById(engineerId);
		if (optionalEngineer.isPresent()) {
			Engineer engineer = optionalEngineer.get();
			engineer.setName(engineerDetails.getName());
			engineerRepository.save(engineer);
			return "Engineer with ID " + engineerId + " updated successfully";
		}
		return "Engineer with ID " + engineerId + " not found";
	}

	@DeleteMapping("/saveEng/{engineerId}")
	public String deleteEngineer(@PathVariable int engineerId) {
		Optional<Engineer> optionalEngineer = engineerRepository.findById(engineerId);
		if (optionalEngineer.isPresent()) {
			engineerRepository.delete(optionalEngineer.get());
			return "Engineer with id " + engineerId + " deleted successfully";
		} else {
			return "Engineer with id " + engineerId + " not found";
		}
	}

}
