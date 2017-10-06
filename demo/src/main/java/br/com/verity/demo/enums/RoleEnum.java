package br.com.verity.demo.enums;

public enum RoleEnum {
	USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");
     
    private String roleEnum;
     
    private RoleEnum(String roleEnum){
        this.roleEnum = roleEnum;
    }
     
    public String getRoleEnum(){
        return roleEnum;
    }
}
