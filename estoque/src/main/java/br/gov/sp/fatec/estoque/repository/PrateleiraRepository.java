package br.gov.sp.fatec.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.estoque.models.Prateleira;

public interface PrateleiraRepository extends JpaRepository<Prateleira, Long>{
	List<Prateleira> findAll();
	
	Prateleira findById(long id);
}
