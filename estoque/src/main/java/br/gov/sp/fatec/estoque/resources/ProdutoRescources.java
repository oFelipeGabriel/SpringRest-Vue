package br.gov.sp.fatec.estoque.resources;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.estoque.models.Produto;
import br.gov.sp.fatec.estoque.repository.ProdutoRepository;

@RestController
@Service("produtoRescource")
@RequestMapping(value="/api")
public class ProdutoRescources {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@RequestMapping("/produto/{id}")
	public ResponseEntity listaProdutoUnico(@PathVariable(value="id") long id){
		Produto p = produtoRepository.findById(id);
		if(p == null) {
			return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body("Error Message");
			}
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(p);
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@GetMapping("/produtos")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	
}
