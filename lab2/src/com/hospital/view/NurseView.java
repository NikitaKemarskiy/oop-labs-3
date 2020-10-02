package com.hospital.view;

public class NurseView extends View {
    @Override
    protected void printOptions() {
        System.out.println("Выберите действие:");
        System.out.println("\t0. Изменить уровень доступа");
        System.out.println("\t1. Список назначений");
        System.out.println("\t2. Выполнить назначение");
        System.out.println("\t3. Выход");
    }

    @Override
    protected boolean validateOption(int option) {
        return option >= 0 && option < 4;
    }
}
