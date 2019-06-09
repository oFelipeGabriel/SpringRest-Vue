package br.gov.sp.fatec.service;

import java.util.List;

import javax.transaction.Transactional;
import java.sql.Date;

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


import br.gov.sp.fatec.model.Prateleira;
import br.gov.sp.fatec.repository.PrateleiraRepository;
import br.gov.sp.fatec.repository.ProdutoRepository;

@Service("prateleiraService")
public class PrateleiraServiceImpl {
	
	@Autowired
	private PrateleiraRepository prateleiraRepo;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void setUsuarioRepo(PrateleiraRepository prateleiraRepo) {
		this.prateleiraRepo = prateleiraRepo;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public Prateleira inlcuirPrateleira(long nota_fiscal, 
			Date data_nota_fiscal, Date data_entrada, Date data_fabricacao,
			long numero_lote, long id_produto) {
		Prateleira p = new Prateleira();
		p.setData_entrada((java.sql.Date) data_nota_fiscal);
		p.setNota_fiscal(nota_fiscal);
		p.setData_entrada(data_entrada);
		p.setData_fabricacao(data_fabricacao);
		p.setNumero_lote(numero_lote);
		p.setProduto(produtoRepository.findById(id_produto));
		prateleiraRepo.save(p);
		return p;
		
	}

}
