package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Engineer> engineers = new ArrayList<>();

	public Project() {
		super();
	}

	public Project(String name, List<Engineer> engineers) {
		super();
		this.name = name;
		this.engineers = engineers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Engineer> getEngineers() {
		return engineers;
	}

	public void setEngineers(List<Engineer> engineers) {
		this.engineers = engineers;
	}

	public void addEngineer(Engineer engineer) {
		engineers.add(engineer);
		engineer.setProject(this);
	}

	public void removeEngineer(Engineer engineer) {
		engineers.remove(engineer);
		engineer.setProject(null);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", engineers=" + engineers + "]";
	}

}
