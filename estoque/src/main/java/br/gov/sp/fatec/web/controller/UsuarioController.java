package br.gov.sp.fatec.web.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.gov.sp.fatec.model.Autorizacao;
import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.AutorizacaoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;
import br.gov.sp.fatec.service.UsuarioService;
import br.gov.sp.fatec.view.View;

@RestController
@CrossOrigin
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private AutorizacaoRepository autorizacaoRepo;


	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@CrossOrigin
	@RequestMapping(value = "/get/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Usuario>> pesquisar(@PathVariable("nome") String nome) {
		return new ResponseEntity<Collection<Usuario>>(usuarioService.buscar(nome), HttpStatus.OK);
	}

	
	
	@CrossOrigin
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public ResponseEntity<Usuario> get(@RequestParam(value="id", defaultValue="1") Long id) {
		Usuario usuario = usuarioService.buscar(id);
		if(usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<Collection<Usuario>> getAll() {
		return new ResponseEntity<Collection<Usuario>>(usuarioService.todos(), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/Usuario", method = RequestMethod.POST)
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
		usuario = usuarioService.salvar(usuario);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById?id=" + usuario.getId()).build().toUri());
		return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/novoUsuario", method = RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestBody ObjectNode json) {
		//Autorizacao autorizacao = autorizacaoRepo.findByNome(nomeAutorizacao);
		//String nome = nome.getParameter("nome");
		//String senha = request.getParameter("senha");
		//String nomeAutorizacao = request.getParameter("nomeAutorizacao");
		String nome = json.get("nome").asText();
		String senha = json.get("senha").asText();
		String nomeAutorizacao = json.get("nomeAutorizacao").asText();
		Usuario usuario = new Usuario();
<<<<<<< HEAD
		System.out.println(senha);
=======
		String nome = json.get("nome").asText();
		String senha = json.get("senha").asText();
		String nomeAutorizacao = json.get("nomeAutorizacao").asText();
		usuario.setAutorizacoes(new ArrayList<Autorizacao>());
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
		usuario.setNome(nome);
		usuario.setSenha(md5(senha));
		if(nomeAutorizacao.equals("ROLE_ADMIN")){
			Autorizacao autorizacao = autorizacaoRepo.findByNome(nomeAutorizacao);
			usuario.getAutorizacoes().add(autorizacao);			
		}
		Autorizacao autorizacao2 = autorizacaoRepo.findByNome("ROLE_USUARIO");
		usuario.getAutorizacoes().add(autorizacao2);	
		usuarioRepo.save(usuario);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
	}
<<<<<<< HEAD

=======
	
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/editaUsuario/{id}")
	public Usuario saveResource(@RequestBody Usuario u,@PathVariable("id") int id) {
		Usuario usuario = usuarioRepo.findById(id);
		usuario.setNome(u.getNome());
		usuario.setSenha(u.getSenha());
		return usuarioRepo.save(usuario);
	}
<<<<<<< HEAD
	
=======

>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path="/deleteUsuario/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		usuarioService.apagar(id);
		return ResponseEntity.noContent().build();
	}
	
	private String md5(String senha) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
	
			StringBuilder hexString = new StringBuilder();
			hexString.append("{MD5}");
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02x", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
			// Unexpected - do nothing
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			// Unexpected - do nothing			
		}
		return senha;
	}
}
