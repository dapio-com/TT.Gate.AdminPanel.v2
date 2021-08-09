package com.ttranz.ttgateadmin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Terminals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long terminal_org_id;

    //private String org_name;

    private String terminal_tid;
    private String terminal_tsp;
    private int terminal_status;

    public Terminals() {
    }

    public Terminals(Long terminal_org_id, String terminal_tid, String terminal_tsp, int terminal_status) {
        this.terminal_org_id = terminal_org_id;
        this.terminal_tid = terminal_tid;
        this.terminal_tsp = terminal_tsp;
        this.terminal_status = terminal_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTerminal_org_id() {
        return terminal_org_id;
    }

//    public String getOrg_name() {
//        return org_name;
//    }
//
//    public void setOrg_name() { this.org_name = org_name;}

    public void setTerminal_org_id(Long terminal_org_id) {
        this.terminal_org_id = terminal_org_id;
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

    public int getTerminal_status() {
        return terminal_status;
    }

    public void setTerminal_status(int terminal_status) {
        this.terminal_status = terminal_status;
    }
}
