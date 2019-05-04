package br.gov.sp.fatec.estoque.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.estoque.models.Prateleira;
import br.gov.sp.fatec.estoque.repository.PrateleiraRepository;

@RestController
@RequestMapping(value="/api")
public class PrateleiraRescources {
	
	@Autowired
	PrateleiraRepository prateleiraRepository;
	
	@CrossOrigin
	@GetMapping("/prateleiras")
	public List<Prateleira> listaPrateleiras(){
		return prateleiraRepository.findAll();
	}
}
