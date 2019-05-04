package br.gov.sp.fatec.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.estoque.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Produto findById(long id);
	
	Produto findTop1ByNome(String nome);
	
	List<Produto> findByFornecedor(String fornecedor);
	
	List<Produto> findAll();
}
