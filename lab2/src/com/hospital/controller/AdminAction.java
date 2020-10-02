package com.hospital.controller;

import com.hospital.model.Storage;
import com.hospital.model.classification.DoctorCategory;
import com.hospital.model.person.Doctor;
import com.hospital.model.person.Nurse;
import com.hospital.model.person.Patient;
import com.hospital.view.AdminView;
import com.hospital.view.View;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AdminAction extends Action {
    public AdminAction(View view, Storage storage) {
        super(view, storage);
    }

    @Override
    public void action(int option) {
        switch (option) {
            // Список врачей
            case 1: {
                getDoctorsList();
                break;
            }
            // Список пациентов
            case 2: {
                getPatientsList();
                break;
            }
            // Список медсестер
            case 3: {
                getNursesList();
                break;
            }
            // Зарегистрировать врача
            case 4: {
                addDoctor();
                break;
            }
            // Зарегистрировать пациента
            case 5: {
                addPatient();
                break;
            }
            // Выписать пациента
            case 6: {
                checkOutPatient();
                break;
            }
            // Назначить пациенту врача
            case 7: {
                assignPatientDoctor();
                break;
            }
            // Выход
            case 8: {
                System.exit(0);
            }
        }
    }

    public void getDoctorsList() {
        AdminView adminView = (AdminView) view;
        List<Doctor> doctors = null;
        int category = adminView.getDoctorCategoryWithAll();
        switch (category) {
            // Все
            case 1: {
                doctors = storage.doctorDao.getAll();
                break;
            }
            // Педиатры
            case 2: {
                doctors = storage.doctorDao.getByCategory(DoctorCategory.PEDIATRICIAN);
                break;
            }
            // Травматологи
            case 3: {
                doctors = storage.doctorDao.getByCategory(DoctorCategory.TRAUMATOLOGIST);
                break;
            }
            // Хирурги
            case 4: {
                doctors = storage.doctorDao.getByCategory(DoctorCategory.SURGEON);
                break;
            }
            // Офтальмологи
            case 5: {
                doctors = storage.doctorDao.getByCategory(DoctorCategory.OPHTHALMOLOGIST);
                break;
            }
        }

        List<Doctor.DoctorPatientsNumber> doctorPatientsNumbers = storage.doctorDao.getDoctorPatientsNumbers(
                doctors,
                storage.patientDao
        );

        int sortAttribute = adminView.getDoctorsSortAttribute();
        switch (sortAttribute) {
            // ФИО
            case 1: {
                doctorPatientsNumbers.sort(
                        Comparator.comparing((Doctor.DoctorPatientsNumber d) -> (d.getDoctor().getName() + d.getDoctor().getSurname()))
                );
                break;
            }
            // Категория
            case 2: {
                doctorPatientsNumbers.sort(
                        Comparator.comparingInt((Doctor.DoctorPatientsNumber d) -> d.getDoctor().getCategory().getValue())
                );
                break;
            }
            // Количество пациентов
            case 3: {
                doctorPatientsNumbers.sort(
                        Comparator.comparingInt(Doctor.DoctorPatientsNumber::getPatientsNumber)
                );
                break;
            }
        }
        if (doctorPatientsNumbers.size() == 0) {
            view.print("Врачи с указанными фильтрами не были найдены");
            return;
        }
        view.printObjects(doctorPatientsNumbers);
    }

    public void getPatientsList() {
        AdminView adminView = (AdminView) view;
        List<Patient> patients = storage.patientDao.getAll();
        int sortAttribute = adminView.getPatientsSortAttribute();
        switch (sortAttribute) {
            // ФИО
            case 1: {
                patients.sort(
                    Comparator.comparing((Patient p) -> (p.getName() + p.getSurname()))
                );
                break;
            }
            // Дата рождения
            case 2: {
                patients.sort(
                    Comparator.comparing((Patient p) -> p.getBirthday())
                );
                break;
            }
        }
        if (patients.size() == 0) {
            System.out.println("Пациенты не были найдены");
            return;
        }
        view.printObjects(patients);
    }

    public void getNursesList() {
        List<Nurse> nurses = storage.nurseDao.getAll();
        view.printObjects(nurses);
    }

    public void addDoctor() {
        AdminView adminView = (AdminView) view;

        String name = view.getString("Введите имя: ");
        String surname = view.getString("Введите фамилию: ");
        Date birthday = view.getDate("Введите дату рождения в формате dd-mm-yyyy: ");

        int category = adminView.getDoctorCategory();
        DoctorCategory doctorCategory = null;
        switch (category) {
            // Педиатры
            case 1: {
                doctorCategory = DoctorCategory.PEDIATRICIAN;
                break;
            }
            // Травматологи
            case 2: {
                doctorCategory = DoctorCategory.TRAUMATOLOGIST;
                break;
            }
            // Хирурги
            case 3: {
                doctorCategory = DoctorCategory.SURGEON;
                break;
            }
            // Офтальмологи
            case 4: {
                doctorCategory = DoctorCategory.OPHTHALMOLOGIST;
                break;
            }
        }

        int currentId = storage.doctorDao.getCurrentId();
        storage.doctorDao.save(new Doctor(currentId++, name, surname, doctorCategory, birthday));
        storage.doctorDao.setCurrentId(currentId);
    }

    public void addPatient() {
        String name = view.getString("Введите имя: ");
        String surname = view.getString("Введите фамилию: ");
        Date birthday = view.getDate("Введите дату рождения в формате dd-mm-yyyy: ");

        int currentId = storage.patientDao.getCurrentId();
        storage.patientDao.save(new Patient(currentId++, name, surname, null, birthday));
        storage.patientDao.setCurrentId(currentId);
    }

    public void checkOutPatient() {
        int id = view.getInt("Введите id пациента: ");
        storage.patientDao.delete(id);
    }

    public void assignPatientDoctor() {
        do {
            int patientId = view.getInt("Введите id пациента: ");
            int doctorId = view.getInt("Введите id врача: ");
            Patient patient = storage.patientDao.get(patientId);
            Doctor doctor = storage.doctorDao.get(doctorId);
            if (patient == null || doctor == null) {
                view.print("Не найден пациент / врач с указанным id. Попробуйте снова");
                continue;
            }
            patient.setDoctor(doctor);
            break;
        } while (true);
    }
}
