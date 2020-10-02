package com.hospital.view;

public class DoctorView extends View {
    @Override
    protected void printOptions() {
        System.out.println("Выберите действие:");
        System.out.println("\t0. Изменить уровень доступа");
        System.out.println("\t1. Список пациентов");
        System.out.println("\t2. Список назначений");
        System.out.println("\t3. Список лечений");
        System.out.println("\t4. Поставить диагноз");
        System.out.println("\t5. Сделать назначение");
        System.out.println("\t6. Выполнить назначение");
        System.out.println("\t7. Выход");
    }

    @Override
    protected boolean validateOption(int option) {
        return option >= 0 && option < 8;
    }
}
