package br.gov.sp.fatec.estoque.resources;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.estoque.models.Produto;
import br.gov.sp.fatec.estoque.repository.ProdutoRepository;

@RestController
@RequestMapping(value="/api")
public class ProdutoRescources {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@RequestMapping("/produto/{id}")
	public ResponseEntity listaAlunoUnico(@PathVariable(value="id") long id){
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
	@GetMapping("/produtos")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Produto> listaAlunos(){
		return produtoRepository.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/alunos")
	@Transactional
	public ResponseEntity salvaALuno(@RequestBody Produto produto) {
		try {
		produtoRepository.save(produto);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(produto);
		}catch (Exception e) {
			return ResponseEntity
		            .status(HttpStatus.METHOD_NOT_ALLOWED)
		            .body("Error Message");
			}
	}
	
}
