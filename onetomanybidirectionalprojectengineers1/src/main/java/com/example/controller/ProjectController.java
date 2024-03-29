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

import com.example.entity.Project;
import com.example.repository.ProjectJPA;

@RestController
public class ProjectController {
	@Autowired
	private ProjectJPA projectRepository;

	@GetMapping("/saveProj/{id}")
	public ResponseEntity<String> getProject(@PathVariable int id) {
		try {
			if (id == 0) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			Optional<Project> projectOptional = projectRepository.findById(id);
			if (projectOptional.isPresent()) {
				Project project = projectOptional.get();
				return new ResponseEntity<>(project.toString() + " fetched!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Project with ID " + id + " not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/saveProj")
	public ResponseEntity<String> saveProject(@RequestBody Project project) {
		try {
			if (project == null || project.getEngineers() == null || project.getEngineers().isEmpty()) {
				return new ResponseEntity<>("Project or Engineers cannot be null or empty!", HttpStatus.BAD_REQUEST);
			}

			project.getEngineers().forEach(engineer -> engineer.setProject(project));

			Project savedProject = projectRepository.save(project);

			return new ResponseEntity<>(savedProject + " saved!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/saveProj/{projectId}")
	public String updateProject(@PathVariable int projectId, @RequestBody Project projectDetails) {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if (optionalProject.isPresent()) {
			Project project = optionalProject.get();
			project.setName(projectDetails.getName());
			projectRepository.save(project);
			return "Project with id " + projectId + " updated successfully";
		} else {
			return "Project with id " + projectId + " not found";
		}
	}

	@DeleteMapping("/saveProj/{projectId}")
	public String deleteProject(@PathVariable int projectId) {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if (optionalProject.isPresent()) {
			projectRepository.delete(optionalProject.get());
			return "Project with id " + projectId + " deleted successfully";
		} else {
			return "Project with id " + projectId + " not found";
		}
	}
}
