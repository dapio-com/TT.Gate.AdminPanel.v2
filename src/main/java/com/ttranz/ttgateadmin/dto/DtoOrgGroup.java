package com.ttranz.ttgateadmin.dto;

public class DtoOrgGroup {

    private Long id;
    private String org_group_name;
    private String org_group_description;

    public DtoOrgGroup(Long id, String org_group_name, String org_group_description) {
        this.id = id;
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
