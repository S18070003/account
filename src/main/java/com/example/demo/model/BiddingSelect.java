package com.example.demo.model;

import java.util.Date;

public class BiddingSelect {
    private String department;
    private Date bidreceivedate;

    private Date bidstopdate;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getBidreceivedate() {
        return bidreceivedate;
    }

    public void setBidreceivedate(Date bidreceivedate) {
        this.bidreceivedate = bidreceivedate;
    }

    public Date getBidstopdate() {
        return bidstopdate;
    }

    public void setBidstopdate(Date bidstopdate) {
        this.bidstopdate = bidstopdate;
    }
}
