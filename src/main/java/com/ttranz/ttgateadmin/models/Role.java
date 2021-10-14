package com.ttranz.ttgateadmin.models;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    SUPER_ADMIN, ADMIN, OPERATOR, AGENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
