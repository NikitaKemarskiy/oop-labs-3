package com.hospital.controller;

import com.hospital.model.Storage;
import com.hospital.model.person.Doctor;
import com.hospital.model.person.Patient;
import com.hospital.model.treatment.Assignment;
import com.hospital.view.AdminView;
import com.hospital.view.View;

import java.util.Comparator;
import java.util.List;

public class DoctorAction extends Action {
    private int id;

    public DoctorAction(View view, Storage storage, int id) {
        super(view, storage);
        this.id = id;
    }

    @Override
    public void action(int option) {
        switch (option) {
            // Список пациентов
            case 1: {
                getPatientsList();
                break;
            }
            // Список назначений
            case 2: {
                getAssignmentsList();
                break;
            }
            // Список лечений
            case 3: {
                getAssignmentsList();
                break;
            }
            // Поставить диагноз
            case 4: {
                makeDiagnosis();
                break;
            }
            // Сделать назначение
            case 5: {
                makeAssignment();
                break;
            }
            // Выполнить назначение
            case 6: {
                break;
            }
            // Выход
            case 7: {
                System.exit(0);
            }
        }
    }

    public void getPatientsList() {
        List<Patient> patients = storage.patientDao.getByDoctorId(id);
        if (patients.size() == 0) {
            System.out.println("Пациенты не были найдены");
            return;
        }
        view.printObjects(patients);
    }

    public void getAssignmentsList() {
        List<Assignment> assignments = storage.assignmentDao.getAll();
        if (assignments.size() == 0) {
            System.out.println("Назначения не были найдены");
            return;
        }
        view.printObjects(assignments);
    }

    public void makeDiagnosis() {
        do {
            int patientId = view.getInt("Введите id пациента: ");
            Patient patient = storage.patientDao.get(patientId);
            if (patient == null) {
                view.print("Не найден пациент с указанным id. Попробуйте снова");
                continue;
            }
            String diagnosis = "Диагноз: " + view.getString("Введите диагноз: ");
            patient.addMedicalCardRecord(diagnosis);
            break;
        } while (true);
    }

    public void makeAssignment() {
        do {
            int patientId = view.getInt("Введите id пациента: ");
            Patient patient = storage.patientDao.get(patientId);
            if (patient == null) {
                view.print("Не найден пациент с указанным id. Попробуйте снова");
                continue;
            }
            break;
        } while (true);
    }
}
