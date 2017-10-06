package br.com.verity.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.verity.demo.entity.RoleEntity;
import br.com.verity.demo.entity.UsuarioEntity;
import br.com.verity.demo.service.RoleService;
import br.com.verity.demo.service.UsuarioService;

@Controller
public class UsuarioController {

	//@Autowired
	//private UsuarioDAO dao;
	@Autowired
	RoleService roleService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
	}
	@RequestMapping(value = {"/senha" }, method = RequestMethod.GET)
	@ResponseBody
    public String hello() {
		
		BCryptPasswordEncoder n = new BCryptPasswordEncoder();
        return n.encode("1") + "__" + n.matches("11", "$2a$10$NtnPVPFEzkKFM5MaSZKpVucOQsRC0WXFTuboyQ4bP1qomGllXqBeK");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/secured/all")
    @ResponseBody
    public String securedHello() {
        return "Secured Hello";
    }

    @GetMapping("/secured/alternate")
    public String alternate() {
        return "alternate";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login/login";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("user", usuario);
        return "newuser";
    }
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid @ModelAttribute("user") UsuarioEntity usuario,
            BindingResult result, ModelMap model) {
 
        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "newuser";
        }
        usuarioService.salvar(usuario);
         
        if(usuario.getRoles()!=null){
            for(RoleEntity profile : usuario.getRoles()){
                System.out.println("Profile : "+ profile.getRole());
            }
        }
         
        return "User " + usuario.getNomeCompleto() + " has been registered successfully";
    }
    @ModelAttribute("roles")
    public List<RoleEntity> initializeProfiles() {
        return roleService.findAll();
    }
}
