package com.hospital.model.treatment;

import java.util.ArrayList;
import java.util.List;

public class MedicalCard {
    private List<MedicalCardRecord> records;

    public MedicalCard() {
        records = new ArrayList<>();
    }

    public void addRecord(String record) {
        records.add(new MedicalCardRecord(record));
    }

    public List<MedicalCardRecord> getRecords() {
        return records;
    }
}
