package com.hospital.view;

public class AdminView extends View {
    /*** Main option getting ***/
    @Override
    protected void printOptions() {
        System.out.println("Выберите действие:");
        System.out.println("\t0. Изменить уровень доступа");
        System.out.println("\t1. Список врачей");
        System.out.println("\t2. Список пациентов");
        System.out.println("\t3. Список медсестер");
        System.out.println("\t4. Зарегистрировать врача");
        System.out.println("\t5. Зарегистрировать пациента");
        System.out.println("\t6. Выписать пациента");
        System.out.println("\t7. Назначить пациенту врача");
        System.out.println("\t8. Выход");
    }

    @Override
    protected boolean validateOption(int option) {
        return option >= 0 && option < 9;
    }

    /*** Doctor category with all getting ***/
    private boolean validateDoctorCategoryWithAll(int option) {
        return option > 0 && option < 6;
    }

    public int getDoctorCategoryWithAll() {
        do {
            System.out.println("Выберите категорию:");
            System.out.println("\t1. Все категории");
            System.out.println("\t2. Педиатры");
            System.out.println("\t3. Травматологи");
            System.out.println("\t4. Хирурги");
            System.out.println("\t5. Офтальмологи");
            int option = scan.nextInt();
            scan.nextLine();
            if (!validateDoctorCategoryWithAll(option)) {
                System.out.println("Введено некорректное значение. Попробуйте снова");
                continue;
            }
            return option;
        } while (true);
    }

    /*** Doctor category getting ***/
    private boolean validateDoctorCategory(int option) {
        return option > 0 && option < 5;
    }

    public int getDoctorCategory() {
        do {
            System.out.println("Выберите категорию:");
            System.out.println("\t1. Педиатры");
            System.out.println("\t2. Травматологи");
            System.out.println("\t3. Хирурги");
            System.out.println("\t4. Офтальмологи");
            int option = scan.nextInt();
            scan.nextLine();
            if (!validateDoctorCategory(option)) {
                System.out.println("Введено некорректное значение. Попробуйте снова");
                continue;
            }
            return option;
        } while (true);
    }

    /*** Doctors sort attribute getting ***/
    public boolean validateDoctorsSortAttribute(int option) {
        return option > 0 && option < 4;
    }

    public int getDoctorsSortAttribute() {
        do {
            System.out.println("Выберите атрибут сортировки:");
            System.out.println("\t1. ФИО");
            System.out.println("\t2. Категория");
            System.out.println("\t3. Количество пациентов");
            int option = scan.nextInt();
            scan.nextLine();
            if (!validateDoctorsSortAttribute(option)) {
                System.out.println("Введено некорректное значение. Попробуйте снова");
                continue;
            }
            return option;
        } while (true);
    }

    /*** Patients sort attribute getting ***/
    public boolean validatePatientsSortAttribute(int option) {
        return option > 0 && option < 3;
    }

    public int getPatientsSortAttribute() {
        do {
            System.out.println("Выберите атрибут сортировки:");
            System.out.println("\t1. ФИО");
            System.out.println("\t2. Дата рождения");
            int option = scan.nextInt();
            scan.nextLine();
            if (!validateDoctorsSortAttribute(option)) {
                System.out.println("Введено некорректное значение. Попробуйте снова");
                continue;
            }
            return option;
        } while (true);
    }
}
