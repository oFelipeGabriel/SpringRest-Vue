package br.gov.sp.fatec.estoque.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.estoque.models.Usuario;
import br.gov.sp.fatec.estoque.repository.UsuarioRepository;

@Service("segurancaService")
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepository = usuarioRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return (UserDetails) usuario;
	}

}
