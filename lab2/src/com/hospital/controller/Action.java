package com.hospital.controller;

import com.hospital.model.Storage;
import com.hospital.view.View;

public abstract class Action {
    protected View view;
    protected Storage storage;

    protected Action(View view, Storage storage) {
        this.view = view;
        this.storage = storage;
    }

    public abstract void action(int option);

    public static Action getAction(AccessLevel accessLevel, View view, Storage storage) throws AccessLevelException {
        switch (accessLevel) {
            case ADMIN: {
                return new AdminAction(view, storage);
            }
            case DOCTOR: {
                int id = view.getInt("Введите id врача: ");
                return new DoctorAction(view, storage, id);
            }
            case NURSE: {
                int id = view.getInt("Введите id медсестры: ");
                return new NurseAction(view, storage, id);
            }
            default: {
                throw new AccessLevelException("Invalid access level specified");
            }
        }
    }
}