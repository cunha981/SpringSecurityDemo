package br.com.verity.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.verity.demo.dao.RoleDAO;
import br.com.verity.demo.entity.RoleEntity;

@Service
public class RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	public RoleEntity findById(Integer id){
		return roleDAO.findOne(id);
	}

	public List<RoleEntity> findAll() {
		// TODO Auto-generated method stub
		return roleDAO.findAll();
	}
}
