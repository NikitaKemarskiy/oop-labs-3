package com.hospital.model;

import com.hospital.model.dao.*;

import java.util.List;

public class Storage {
    private static Storage storage;
    public AssignmentDao assignmentDao;
    public DoctorDao doctorDao;
    public MedicineDao medicineDao;
    public NurseDao nurseDao;
    public OperationDao operationDao;
    public PatientDao patientDao;
    public ProcedureDao procedureDao;

    private Storage() {
        assignmentDao = new AssignmentDao();
        doctorDao = new DoctorDao();
        medicineDao = new MedicineDao();
        nurseDao = new NurseDao();
        operationDao = new OperationDao();
        patientDao = new PatientDao(doctorDao);
        procedureDao = new ProcedureDao();
    }

    public static Storage getStorage() {
        return storage == null
            ? storage = new Storage()
            : storage;
    }
}
