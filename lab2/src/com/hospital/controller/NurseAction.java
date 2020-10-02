package com.hospital.controller;

import com.hospital.model.Storage;
import com.hospital.view.View;

public class NurseAction extends Action {
    private int id;

    public NurseAction(View view, Storage storage, int id) {
        super(view, storage);
        this.id = id;
    }

    @Override
    public void action(int option) {
        //...
    }
}
