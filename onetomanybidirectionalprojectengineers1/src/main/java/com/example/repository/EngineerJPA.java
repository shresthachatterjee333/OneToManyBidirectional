package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Engineer;

public interface EngineerJPA extends JpaRepository<Engineer, Integer> {

}
