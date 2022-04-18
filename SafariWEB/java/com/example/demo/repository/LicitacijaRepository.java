package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Licitacija;
import model.Predmet;

public interface LicitacijaRepository extends JpaRepository<Licitacija, Integer> {
	
	List<Licitacija> findByPredmet(Predmet p, Sort s);

}
