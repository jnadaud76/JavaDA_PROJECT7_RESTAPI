package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private Integer id;
    @Length(max=125)
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Pattern(regexp="(?=.{8,20}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$")
    @Length(min=8,max=20)
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Length(max=125)
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @Length(max=125)
    @NotBlank(message = "Role is mandatory")
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
