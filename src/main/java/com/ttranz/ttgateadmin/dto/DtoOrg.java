package com.ttranz.ttgateadmin.dto;

public class DtoOrg {

    private Long id;
    private String org_group_name;
    private Long org_group_id;
    private String org_name;
    private String org_owner;


    public DtoOrg(Long id, String org_group_name, Long org_group_id, String org_name, String org_owner) {
        this.id = id;
        this.org_group_name = org_group_name;
        this.org_group_id = org_group_id;
        this.org_name = org_name;
        this.org_owner = org_owner;
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

    public Long getOrg_group_id() {
        return org_group_id;
    }

    public void setOrg_group_id(Long org_group_id) {
        this.org_group_id = org_group_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_owner() {
        return org_owner;
    }

    public void setOrg_owner(String org_owner) {
        this.org_owner = org_owner;
    }
}
