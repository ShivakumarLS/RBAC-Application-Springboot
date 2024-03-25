package com.shivu.userapplication.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer departmentId;

    private String departmentName;

    @OneToMany
    @JoinTable(
        name="user_department_junction",
        joinColumns = {@JoinColumn(name="dept_id")},
        inverseJoinColumns = {@JoinColumn(name="user_id")}
    )
    private List<ApplicationUser> employees ;

    public Department() {
    }

  
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(Integer departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
