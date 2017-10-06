package br.com.verity.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.verity.demo.dao.UsuarioDAO;
import br.com.verity.demo.entity.UsuarioEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UsuarioDAO usuarioDAO;


    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Optional<UsuarioEntity> optionalUsuarios = usuarioDAO.findByUsuario(usuario);

        optionalUsuarios
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return optionalUsuarios
                .map(CustomUserDetails::new).get();
    }
}
