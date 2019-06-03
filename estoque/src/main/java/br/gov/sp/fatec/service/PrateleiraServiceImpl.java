package br.gov.sp.fatec.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.model.Prateleira;
import br.gov.sp.fatec.repository.PrateleiraRepository;



@RestController
@Service("prateleiraResource")
@RequestMapping(value="/api")
public class PrateleiraServiceImpl {
	@Autowired
	PrateleiraRepository prateleiraRepository;
	
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/novoEstoque", method = RequestMethod.POST)
	public Prateleira novoPrateleira(@RequestBody Prateleira p){
		return prateleiraRepository.save(p);
	}
	
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@GetMapping("/prateleiras")
	public List<Prateleira> listaProdutos(){
		return prateleiraRepository.findAll();
	}
	
}