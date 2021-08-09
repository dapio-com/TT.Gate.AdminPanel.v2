package com.ttranz.ttgateadmin.models;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timestamp;
    private String org;
    private String ip;
    private String terminal_tid;
    private String terminal_tsp;
    private String error_text;


    public Error() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTerminal_tid() {
        return terminal_tid;
    }

    public void setTerminal_tid(String terminal_tid) {
        this.terminal_tid = terminal_tid;
    }

    public String getTerminal_tsp() {
        return terminal_tsp;
    }

    public void setTerminal_tsp(String terminal_tsp) {
        this.terminal_tsp = terminal_tsp;
    }

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }
}
