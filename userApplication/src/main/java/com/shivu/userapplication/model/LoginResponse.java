package com.shivu.userapplication.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoginResponse {
 private String username;

    private String departmentName;

    private Collection<? extends GrantedAuthority> roles;

    public String getUsername() {
        return username;
    }

    public LoginResponse(String username, String departmentName, Collection<? extends GrantedAuthority> roles,
            String jwt) {
        this.username = username;
        this.departmentName = departmentName;
        this.roles = roles;
        this.jwt = jwt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    private String jwt;
}
