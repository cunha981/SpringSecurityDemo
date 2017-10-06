package br.com.verity.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbUsuario")
public class UsuarioEntity{
	@Id
	@Column(name = "usuarioId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "usuario")
	@NotEmpty
	private String usuario;
	
	@Column(name = "senha")
	@NotEmpty @Size(min = 3)
	private String senha;
	
	@Column(name = "nmCompleto")
	@NotEmpty
	private String nomeCompleto;
	
	@Column(name="email")
	private String email;
	
	@Column(name = "flAtivo")
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "tbUsuarioRole", joinColumns = @JoinColumn(name = "usuarioId"), 
		inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();
	
	
	public UsuarioEntity(){
		
	}
	
	public UsuarioEntity(UsuarioEntity usuario) {
		this.id = usuario.getId();
		this.usuario = usuario.getUsuario();
		this.senha = usuario.getSenha();
		this.nomeCompleto = usuario.getNomeCompleto();
		this.email = usuario.getEmail();
		this.ativo = usuario.getAtivo();
		this.roles = usuario.getRoles();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
}
