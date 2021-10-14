package com.ttranz.ttgateadmin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrgGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String org_group_name;
    private String org_group_description;

    public OrgGroup() {
    }

    public OrgGroup(String org_group_name, String org_group_description) {
        this.org_group_name = org_group_name;
        this.org_group_description = org_group_description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrg_group_name() {
        return org_group_name;
    }

    public void setOrg_group_name(String org_group_name) {
        this.org_group_name = org_group_name;
    }

    public String getOrg_group_description() {
        return org_group_description;
    }

    public void setOrg_group_description(String org_group_description) {
        this.org_group_description = org_group_description;
    }
}
