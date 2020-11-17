package com.nikita.controller.command.doctor;

import com.nikita.controller.command.Command;
import com.nikita.model.service.MedicalCardRecordService;

public class DoctorCreateMedicalCardRecordTreatmentCommand implements Command {
        private int medicalCardRecordId;
        private int treatmentId;
        private int amount;
        private MedicalCardRecordService medicalCardRecordService;

        public DoctorCreateMedicalCardRecordTreatmentCommand(
            int medicalCardRecordId,
            int treatmentId,
            int amount
        ) {
            this.medicalCardRecordId = medicalCardRecordId;
            this.treatmentId = treatmentId;
            this.amount = amount;
            medicalCardRecordService = new MedicalCardRecordService();
        }

        public Object execute() {
            medicalCardRecordService.addTreatmentToMedicalCardRecord(
                medicalCardRecordId,
                treatmentId,
                amount,
                amount
            );
            return null;
        }
    }

