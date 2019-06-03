package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.model.Prateleira;

public interface PrateleiraRepository extends JpaRepository<Prateleira, Long>{
	List<Prateleira> findAll();
	
	Prateleira findById(long id);
	
	Prateleira save(Prateleira p);
}