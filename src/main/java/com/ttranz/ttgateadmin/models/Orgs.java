package com.ttranz.ttgateadmin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orgs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String org_name;
    private String org_owner;

    public Orgs() {
    }

    public Orgs(String org_name, String org_owner){
        this.org_name = org_name;
        this.org_owner = org_owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_owner() { return org_owner; }

    public void setOrg_owner(String org_owner) {
        this.org_owner = org_owner;
    }
}
