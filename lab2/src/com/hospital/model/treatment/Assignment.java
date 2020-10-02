package com.hospital.model.treatment;

import com.hospital.model.dao.IdObject;
import com.hospital.model.person.Patient;

import java.util.Map;

public class Assignment extends IdObject {
    private Map<Treatment, Integer> treatments;
    private final Patient patient;

    public Assignment(int id, Map<Treatment, Integer> treatments, Patient patient) {
        super(id);
        this.treatments = treatments;
        this.patient = patient;
    }

    public void updateTreatmentNumber(Treatment treatment, int difference) {
        if (!treatments.containsKey(treatment)) { return; }
        Integer number = treatments.get(treatment);
        treatments.replace(treatment, number + difference);
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        String treatmentsString = "{";
        for (Map.Entry<Treatment, Integer> treatment : treatments.entrySet()) {
            treatmentsString += treatment.getKey().getName() + " x " + treatment.getValue() + ";";
        }
        treatmentsString += "}";
        return super.toString() + String.format(
            "; ФИО пациента: %s %s; Лечение: %s",
            patient.getName(),
            patient.getSurname(),
            treatmentsString
        );
    }
}
