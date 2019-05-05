package br.gov.sp.fatec.estoque.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.estoque.models.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{
	Usuario findByUsername(String username);
	
	List<Usuario> findAll();
}
