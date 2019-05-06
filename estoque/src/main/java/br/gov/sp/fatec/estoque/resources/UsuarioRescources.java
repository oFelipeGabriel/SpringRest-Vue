package br.gov.sp.fatec.estoque.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.estoque.models.Usuario;
import br.gov.sp.fatec.estoque.repository.UsuarioRepository;

@RestController
@RequestMapping(value="/api")
public class UsuarioRescources {
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@CrossOrigin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usuarios")
	public List<Usuario> listarUsuarios(){
		return usuarioRepo.findAll();
	}
}
