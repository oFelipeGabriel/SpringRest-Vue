package br.gov.sp.fatec.resource;


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
@RequestMapping(value="/apiEstoque")
public class PrateleiraResources {
	
	@Autowired
	PrateleiraRepository prateleiraRepository;
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/prateleiras")
	public List<Prateleira> listaPrateleiras(){
		System.out.print( prateleiraRepository.findAll());
		return prateleiraRepository.findAll();
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/novoEstoque", method = RequestMethod.POST)
	public Prateleira novoProduto(@RequestBody Prateleira p){
		return prateleiraRepository.save(p);
	}
}