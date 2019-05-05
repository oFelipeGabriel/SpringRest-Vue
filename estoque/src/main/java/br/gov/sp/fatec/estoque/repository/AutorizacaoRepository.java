package br.gov.sp.fatec.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.estoque.models.Autorizacao;

@Repository
public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>{
	Autorizacao findByNome(String nome);
	
	Optional<Autorizacao> findById(Long id); 
	
}
