package com.ttranz.ttgateadmin.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private String userName;
    private String userPassword;
    private String userDescription;
    private boolean userStatus;
    private Long userOrgGroupId = 0L;
    private Long userOrgId = 0L;

    @Transient
    private String userOrgGroupName = "";
    @Transient
    private String userOrgName = "";


    public User() {

    }

    public User(String userName, String userPassword, String userDescription, Set<Role> roles, Long userOrgGroupId, Long userOrgId) {

        this.roles = roles;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userDescription = userDescription;
        this.userStatus = true;
        this.userOrgGroupId = userOrgGroupId;
        this.userOrgId = userOrgId;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Long getUserOrgGroupId() {
        return userOrgGroupId;
    }

    public void setUserOrgGroupId(Long userOrgGroupId) {
        this.userOrgGroupId = userOrgGroupId;
    }

    public Long getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(Long userOrgId) {
        this.userOrgId = userOrgId;
    }


    public void setUserOrgGroupName(String userOrgGroupName) {
        this.userOrgGroupName = userOrgGroupName;
    }

    public void setUserOrgName(String userOrgName) {
        this.userOrgName = userOrgName;
    }

    public String getUserOrgGroupName() {
        return userOrgGroupName;
    }

    public String getUserOrgName() {
        return userOrgName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userStatus;
    }
}
