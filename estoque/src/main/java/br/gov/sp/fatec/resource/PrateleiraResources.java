package br.gov.sp.fatec.resource;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.gov.sp.fatec.model.Prateleira;
import br.gov.sp.fatec.repository.PrateleiraRepository;

@RestController
@CrossOrigin
@RequestMapping(value="/apiEstoque")
public class PrateleiraResources {
	
	@Autowired
	PrateleiraRepository prateleiraRepository;
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@GetMapping("/prateleiras")
	public List<Prateleira> listaPrateleiras(){
		return prateleiraRepository.findAllByOrderByValidadeAsc();
	}

	@CrossOrigin
	@Transactional
	@RequestMapping(value = "/novoEstoque2", method = RequestMethod.POST)
	public ResponseEntity<Prateleira> salvar(@RequestBody Prateleira p){
		prateleiraRepository.save(p);		
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<Prateleira>(p, responseHeaders, HttpStatus.CREATED);
	}
	
	
}