package com.ttranz.ttgateadmin.models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date op_date_time;

    private Long op_org_group_id;
    private Long op_org_id;
    private String op_tsp;
    private String op_ip;
    private String op_tid;
    private String op_mti;
    private String op_stan;
    private String op_rrn;
    private String op_auth_code;
    private String op_card_num;
    private BigDecimal op_amount;
    private String op_bill_name;
    private String op_xadd01;
    private String op_xadd02;
    private String op_xadd03;
    private String op_xadd04;
    private String op_xadd05;
    private String op_xadd06;
    private String op_xadd07;
    private String op_xadd08;
    private String op_xadd09;
    private String op_xadd10;
    private int op_status;


    public Operation() {
    }

//    public Operation(Long id, Date op_date_time, Long op_org_group_id, Long op_org_id, String op_tsp, String op_ip, String op_tid, String op_mti, String op_stan, String op_rrn, String op_auth_code, String op_card_num, BigDecimal op_amount, String op_bill_name, String op_xadd01, String op_xadd02, String op_xadd03, String op_xadd04, String op_xadd05, String op_xadd06, String op_xadd07, String op_xadd08, String op_xadd09, String op_xadd10, int op_status) {
//        this.id = id;
//        this.op_date_time = op_date_time;
//        this.op_org_group_id = op_org_group_id;
//        this.op_org_id = op_org_id;
//        this.op_tsp = op_tsp;
//        this.op_ip = op_ip;
//        this.op_tid = op_tid;
//        this.op_mti = op_mti;
//        this.op_stan = op_stan;
//        this.op_rrn = op_rrn;
//        this.op_auth_code = op_auth_code;
//        this.op_card_num = op_card_num;
//        this.op_amount = op_amount;
//        this.op_bill_name = op_bill_name;
//        this.op_xadd01 = op_xadd01;
//        this.op_xadd02 = op_xadd02;
//        this.op_xadd03 = op_xadd03;
//        this.op_xadd04 = op_xadd04;
//        this.op_xadd05 = op_xadd05;
//        this.op_xadd06 = op_xadd06;
//        this.op_xadd07 = op_xadd07;
//        this.op_xadd08 = op_xadd08;
//        this.op_xadd09 = op_xadd09;
//        this.op_xadd10 = op_xadd10;
//        this.op_status = op_status;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOp_date_time() {
        return op_date_time;
    }

    public void setOp_date_time(Date op_date_time) {
        this.op_date_time = op_date_time;
    }

    public Long getOp_org_group_id() {
        return op_org_group_id;
    }

    public void setOp_org_group_id(Long op_org_group_id) {
        this.op_org_group_id = op_org_group_id;
    }

    public Long getOp_org_id() {
        return op_org_id;
    }

    public void setOp_org_id(Long op_org_id) {
        this.op_org_id = op_org_id;
    }

    public String getOp_tsp() {
        return op_tsp;
    }

    public void setOp_tsp(String op_tsp) {
        this.op_tsp = op_tsp;
    }

    public String getOp_ip() {
        return op_ip;
    }

    public void setOp_ip(String op_ip) {
        this.op_ip = op_ip;
    }

    public String getOp_tid() {
        return op_tid;
    }

    public void setOp_tid(String op_tid) {
        this.op_tid = op_tid;
    }

    public String getOp_mti() {
        return op_mti;
    }

    public void setOp_mti(String op_mti) {
        this.op_mti = op_mti;
    }

    public String getOp_stan() {
        return op_stan;
    }

    public void setOp_stan(String op_stan) {
        this.op_stan = op_stan;
    }

    public String getOp_rrn() {
        return op_rrn;
    }

    public void setOp_rrn(String op_rrn) {
        this.op_rrn = op_rrn;
    }

    public String getOp_auth_code() {
        return op_auth_code;
    }

    public void setOp_auth_code(String op_auth_code) {
        this.op_auth_code = op_auth_code;
    }

    public String getOp_card_num() {
        return op_card_num;
    }

    public void setOp_card_num(String op_card_num) {
        this.op_card_num = op_card_num;
    }

    public BigDecimal getOp_amount() {
        return op_amount;
    }

    public void setOp_amount(BigDecimal op_amount) {
        this.op_amount = op_amount;
    }

    public String getOp_bill_name() {
        return op_bill_name;
    }

    public void setOp_bill_name(String op_bill_name) {
        this.op_bill_name = op_bill_name;
    }

    public String getOp_xadd01() {
        return op_xadd01;
    }

    public void setOp_xadd01(String op_xadd01) {
        this.op_xadd01 = op_xadd01;
    }

    public String getOp_xadd02() {
        return op_xadd02;
    }

    public void setOp_xadd02(String op_xadd02) {
        this.op_xadd02 = op_xadd02;
    }

    public String getOp_xadd03() {
        return op_xadd03;
    }

    public void setOp_xadd03(String op_xadd03) {
        this.op_xadd03 = op_xadd03;
    }

    public String getOp_xadd04() {
        return op_xadd04;
    }

    public void setOp_xadd04(String op_xadd04) {
        this.op_xadd04 = op_xadd04;
    }

    public String getOp_xadd05() {
        return op_xadd05;
    }

    public void setOp_xadd05(String op_xadd05) {
        this.op_xadd05 = op_xadd05;
    }

    public String getOp_xadd06() {
        return op_xadd06;
    }

    public void setOp_xadd06(String op_xadd06) {
        this.op_xadd06 = op_xadd06;
    }

    public String getOp_xadd07() {
        return op_xadd07;
    }

    public void setOp_xadd07(String op_xadd07) {
        this.op_xadd07 = op_xadd07;
    }

    public String getOp_xadd08() {
        return op_xadd08;
    }

    public void setOp_xadd08(String op_xadd08) {
        this.op_xadd08 = op_xadd08;
    }

    public String getOp_xadd09() {
        return op_xadd09;
    }

    public void setOp_xadd09(String op_xadd09) {
        this.op_xadd09 = op_xadd09;
    }

    public String getOp_xadd10() {
        return op_xadd10;
    }

    public void setOp_xadd10(String op_xadd10) {
        this.op_xadd10 = op_xadd10;
    }

    public int getOp_status() {
        return op_status;
    }

    public void setOp_status(int op_status) {
        this.op_status = op_status;
    }
}
