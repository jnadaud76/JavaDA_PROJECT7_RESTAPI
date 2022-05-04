package com.nnk.springboot.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserDto {

    private Integer id;
    @Size(max=125)
    @Column(unique=true)
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Pattern(regexp="(?=.{8,20}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$",
            message = "Password must contain an uppercase letter a\n" +
            "lowercase letter a number a special character and be between 8 and 20 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Size(max=125)
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @Size(max=125)
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
