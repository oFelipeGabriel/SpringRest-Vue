package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.model.Prateleira;

public interface PrateleiraRepository extends JpaRepository<Prateleira, Long>{
	List<Prateleira> findAll();
	
	Prateleira findById(long id);
	
<<<<<<< HEAD
	Prateleira save(Prateleira p);
=======
	List<Prateleira> findAllByOrderByValidadeAsc();
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
}