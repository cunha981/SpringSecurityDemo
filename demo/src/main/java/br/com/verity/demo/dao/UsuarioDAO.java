package br.com.verity.demo.dao;

import java.util.Optional;

import br.com.verity.demo.entity.UsuarioEntity;

public interface UsuarioDAO extends GenericDAO<UsuarioEntity>{

	Optional<UsuarioEntity> findByUsuario(String usuario);

}
