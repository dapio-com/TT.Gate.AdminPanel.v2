package com.ttranz.ttgateadmin.models;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Error{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long error_timestamp;


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date error_date_time;



    private String error_org_name;
    private String error_ip;
    private String error_tid;
    private String error_tsp;
    private String error_text;


    public Error() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getError_date_time() {
        return error_date_time;
    }

    public void setError_date_time(Date error_timestamp) {
        this.error_date_time = error_timestamp;
    }

    public String getError_org_name() {
        return error_org_name;
    }

    public void setError_org_name(String error_org_name) {
        this.error_org_name = error_org_name;
    }

    public String getError_ip() {
        return error_ip;
    }

    public void setError_ip(String error_ip) {
        this.error_ip = error_ip;
    }

    public String getError_tid() {
        return error_tid;
    }

    public void setError_tid(String error_tid) {
        this.error_tid = error_tid;
    }

    public String getError_tsp() {
        return error_tsp;
    }

    public void setError_tsp(String error_tsp) {
        this.error_tsp = error_tsp;
    }

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }
}
