package ru.happyshark.java.ee.service.repr;

import ru.happyshark.java.ee.persist.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserRepr implements Serializable {

    private Long id;

    private String login;

    private String password;

    private Set<RoleRepr> roles;

    public UserRepr() {
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = new HashSet<>();
        user.getRoles().forEach(r -> this.roles.add(new RoleRepr(r)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleRepr> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleRepr> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserRepr{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
