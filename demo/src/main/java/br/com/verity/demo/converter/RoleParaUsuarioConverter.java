package br.com.verity.demo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.verity.demo.entity.RoleEntity;
import br.com.verity.demo.service.RoleService;

@Component
public class RoleParaUsuarioConverter implements Converter<Object, RoleEntity> {

	@Autowired
	private RoleService roleService;
	
	/*
     * Gets RoleEntity by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
	public RoleEntity convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        RoleEntity role= roleService.findById(id);
        System.out.println("Profile : "+role);
        return role;
    }
}
