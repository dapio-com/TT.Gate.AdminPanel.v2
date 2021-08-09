package com.ttranz.ttgateadmin.dto;

public class DtoOrgsAutocomplete {


    private Long org_id;
    private String org_name;


    public DtoOrgsAutocomplete(Long org_id, String org_name) {
        this.org_id = org_id;
        this.org_name = org_name;
    }

    public Long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Long org_id) {
        this.org_id = org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }
}
