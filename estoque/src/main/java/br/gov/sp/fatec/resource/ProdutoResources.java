package br.gov.sp.fatec.resource;

import java.io.Serializable;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.repository.ProdutoRepository;

@RestController
@Service("produtoRescource")
@RequestMapping(value="/api")
public class ProdutoResources {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@RequestMapping("/produto/{id}")
	public ResponseEntity<Serializable> listaProdutoUnico(@PathVariable(value="id") long id){
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
	@RequestMapping(value = "/novoProduto", method = RequestMethod.POST)
	public Produto novoProduto(@RequestBody Produto p){
		return produtoRepository.save(p);
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/busca/{nome}")
	public List<Produto> buscaPorNome(@PathVariable(value="nome") String nome){
		return produtoRepository.findByNomeContaining(nome);
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@GetMapping("/produtos")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAllByOrderByNomeAsc();
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/editaProduto/{id}")
	public Produto saveResource(@RequestBody Produto p,@PathVariable("id") int id) {
		Produto prod = produtoRepository.findById(id);
		prod.setNome(p.getNome());
		prod.setFornecedor(p.getFornecedor());
		prod.setPeriodo_validade(p.getPeriodo_validade());
		prod.setTemp_armazemnagem(p.getTemp_armazemnagem());
		return produtoRepository.save(prod);
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path="/deleteProduto/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	 
	}
}