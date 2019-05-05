package br.gov.sp.fatec.estoque.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import br.gov.sp.fatec.estoque.models.Usuario;

public class JwtAuthenticationFilter extends GenericFilterBean{
	private String tokenHeader = "Authorization";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			String authorization = servletRequest.getHeader(tokenHeader);
			if (authorization != null) {
				Usuario usuario = JwtUtils.parseToken(authorization.replaceAll("Bearer ", ""));
				UsernamePasswordAuthenticationToken credentials =
						new UsernamePasswordAuthenticationToken(usuario.getUsername(),
								usuario.getSenha(), (Collection<? extends GrantedAuthority>) usuario.getAutorizacoes());
				SecurityContextHolder.getContext().setAuthentication(credentials);
			}
			chain.doFilter(request, response);
		}
		catch(Throwable t) {
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, t.getMessage());
		}
	}
}
