package com.ttranz.ttgateadmin.dto;

public class DtoOrgsAutocomplete {


    private Long id;
    private String org_name;


    public DtoOrgsAutocomplete(Long id, String org_name) {
        this.id = id;
        this.org_name = org_name;
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
}
