package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Project;

public interface ProjectJPA extends JpaRepository<Project, Integer> {

}
