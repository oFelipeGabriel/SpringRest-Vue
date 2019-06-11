package br.gov.sp.fatec.web.controller;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.gov.sp.fatec.model.Prateleira;
import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.repository.PrateleiraRepository;
import br.gov.sp.fatec.repository.ProdutoRepository;


@RestController
@CrossOrigin
@RequestMapping(value = "/apiEstoque")
public class PrateleiraController {
	
	@Autowired
	private PrateleiraRepository prateleiraRepo;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	/*
	 * long nota_fiscal, 
			Date data_nota_fiscal, Date data_entrada, Date data_fabricacao,
			long numero_lote, long id_produto
	 */

	@CrossOrigin
	@GetMapping("/prateleiras")
	public List<Prateleira> listaPrateleiras(){
		return prateleiraRepo.findAllByOrderByValidadeAsc();
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/novoEstoque", method = RequestMethod.POST)
	public ResponseEntity<Prateleira> salvar(@RequestBody ObjectNode json) throws ParseException {
		Prateleira p = new Prateleira();
		Long nota_fiscal = Long.parseLong(json.get("nota_fiscal").asText());
		Date data_nota_fiscal = Date.valueOf(json.get("data_nota_fiscal").asText());
		Date data_entrada = Date.valueOf(json.get("data_entrada").asText());
		Date data_fabricacao = Date.valueOf(json.get("data_fabricacao").asText());
		String numero_lote = json.get("numero_lote").asText();
		String id_produto = json.get("id_produto").asText();
		p.setNota_fiscal(nota_fiscal);
		
		p.setData_nota_fiscal(data_nota_fiscal);
		p.setData_entrada(data_entrada);
		p.setData_fabricacao(data_fabricacao);
		p.setNumero_lote(Long.parseLong(numero_lote));
		Produto produto = produtoRepository.findById(Long.parseLong(id_produto));
		int validade = produto.getPeriodo_validade();
		p.setValidade(addDays(data_fabricacao, validade));
		p.setProduto(produto);
		prateleiraRepo.save(p);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<Prateleira>(p, responseHeaders, HttpStatus.CREATED);
	}
	
	public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
	
	@CrossOrigin
	@RequestMapping(value="/novoEstoque", method = RequestMethod.OPTIONS)
    ResponseEntity<?> collectionOptions()
    {
         return ResponseEntity
                 .ok()
                 .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                 .build();
    }

}
