package br.gov.sp.fatec.estoque.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.sp.fatec.estoque.models.Usuario;
import br.gov.sp.fatec.estoque.security.JwtUtils;

public class LoginController {
	@Autowired
	private AuthenticationManager auth;
	@Autowired
	private UserDetailsService segurancaService;
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<Usuario> login(@RequestBody Usuario login,
			HttpServletResponse response) throws JsonProcessingException {
		UsernamePasswordAuthenticationToken credentials =
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getSenha());
		Usuario usuario = (Usuario) auth.authenticate(credentials).getPrincipal();
		response.setHeader("Token", JwtUtils.generateToken(usuario));
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

}
