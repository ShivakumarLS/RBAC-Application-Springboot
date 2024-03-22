package com.shivu.userapplication.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class DisplayEmployees {

    private String username;

    private String departmentName;

    private Collection<? extends GrantedAuthority> roles;

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public DisplayEmployees(String username, String departmentName, Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.departmentName = departmentName;
        this.roles = roles;
    }

    public DisplayEmployees(String username, String departmentName) {
        this.username = username;
        this.departmentName = departmentName;
    }

    public String getUsername() {
        return username;
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
   
}
