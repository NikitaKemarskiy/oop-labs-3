package com.hospital.view;

import com.hospital.controller.AccessLevel;
import com.hospital.controller.AccessLevelException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public abstract class View {
    protected Scanner scan;

    protected static String DELIMITER_LINE = "-----------------------";

    protected abstract void printOptions();
    protected abstract boolean validateOption(int option);

    protected View() {
        scan = new Scanner(System.in);
    }

    public static AccessLevel getAccessLevel() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Выберите уровень доступа:");
            System.out.println("\t1. Администратор");
            System.out.println("\t2. Доктор");
            System.out.println("\t3. Медсестра");

            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1: {
                    return AccessLevel.ADMIN;
                }
                case 2: {
                    return AccessLevel.DOCTOR;
                }
                case 3: {
                    return AccessLevel.NURSE;
                }
                default: {
                    System.out.println("Введено некорректное значение. Попробуйте снова");
                    continue;
                }
            }
        } while (true);
    }

    public static View getView(AccessLevel accessLevel) throws AccessLevelException {
        switch (accessLevel) {
            case ADMIN: {
                return new AdminView();
            }
            case DOCTOR: {
                return new DoctorView();
            }
            case NURSE: {
                return new NurseView();
            }
            default: {
                throw new AccessLevelException("Invalid access level specified");
            }
        }
    }

    public int getOption() {
        do {
            printOptions();
            int option = scan.nextInt();
            scan.nextLine();
            if (!validateOption(option)) {
                System.out.println("Введено некорректное значение. Попробуйте снова");
                continue;
            }
            return option;
        } while (true);
    }

    public void print(String str) {
        System.out.println(str);
    }

    public <T> void printObjects(List<T> list) {
        System.out.println(DELIMITER_LINE);
        for (Object item : list) {
            System.out.println(item);
        }
        System.out.println(DELIMITER_LINE);
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        int number = scan.nextInt();
        scan.nextLine();
        return number;
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }

    public Date getDate(String prompt) {
        do {
            try {
                String date = getString(prompt);
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
                return format.parse(date);
            } catch (ParseException err) {
                System.out.println("Введено некорректное значение. Попробуйте снова");
                continue;
            }
        } while (true);
    }
}
