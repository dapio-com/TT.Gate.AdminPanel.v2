package com.ttranz.ttgateadmin.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

public class DtoChart {



    private Date op_date_time;
    //private Timestamp op_date_time;
    private BigDecimal op_amount;


    public DtoChart(Date op_date_time, BigDecimal op_amount) {
        this.op_date_time = op_date_time;
        this.op_amount = op_amount;
    }

    public Date getOp_date_time() {
        return op_date_time;
    }

    public void setOp_date_time(Date op_date_time) {
        this.op_date_time = op_date_time;
    }

    public BigDecimal getOp_amount() {
        return op_amount;
    }

    public void setOp_amount(BigDecimal op_amount) {
        this.op_amount = op_amount;
    }
}
