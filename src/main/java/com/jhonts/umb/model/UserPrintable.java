package com.jhonts.umb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserPrintable {
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String name;

    private Set<Role> roles = new HashSet<>();

    public UserPrintable() {}

    public UserPrintable(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
