package br.com.verity.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.verity.demo.dao.UsuarioDAO;
import br.com.verity.demo.entity.UsuarioEntity;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public void salvar(UsuarioEntity usuario){
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		
		usuarioDAO.save(usuario);
	}
	
}
