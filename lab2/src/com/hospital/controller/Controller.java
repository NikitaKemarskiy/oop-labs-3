package com.hospital.controller;

import com.hospital.model.Storage;
import com.hospital.view.View;

public class Controller {
    private AccessLevel accessLevel;
    private View view;
    private Action action;
    private Storage storage;

    private void initAction() throws AccessLevelException {
        accessLevel = View.getAccessLevel();
        view = View.getView(accessLevel);
        action = Action.getAction(accessLevel, view, storage);
    }

    public Controller() {
        storage = Storage.getStorage();
    }

    public void start() {
        try {
            initAction();
            do {
                int option = view.getOption();
                if (option == 0) {
                    initAction();
                } else {
                    action.action(option);
                }
            } while (true);
        } catch (AccessLevelException err) {
            System.err.println(err);
            System.exit(1);
        }
    }
}
