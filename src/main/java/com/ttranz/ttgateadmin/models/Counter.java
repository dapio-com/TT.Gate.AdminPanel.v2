package com.ttranz.ttgateadmin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int total_orgs;
    private int total_terminals;
    private int total_operations;


    public Counter() {
    }

//    public Counters(Long id, int total_operations, int total_orgs, int total_terminals){
//        this.id = id;
//        this.total_operations = total_operations;
//        this.total_orgs = total_orgs;
//        this.total_terminals = total_terminals;
//    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotal_orgs() {
        return total_orgs;
    }

    public void setTotal_orgs(int total_orgs) {
        this.total_orgs = total_orgs;
    }

    public int getTotal_terminals() {
        return total_terminals;
    }

    public void setTotal_terminals(int total_terminals) {
        this.total_terminals = total_terminals;
    }

    public int getTotal_operations() {
        return total_operations;
    }

    public void setTotal_operations(int total_operations) {
        this.total_operations = total_operations;
    }
}
