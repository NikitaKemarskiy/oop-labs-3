package com.hospital.model.treatment;

import java.util.Date;

public class MedicalCardRecord {
    private String record;
    private Date createdAt;

    public MedicalCardRecord(String record) {
        this.record = record;
        createdAt = new Date();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
