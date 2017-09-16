package com.sunshineapp.backend.models;

import com.sunshineapp.backend.annotations.Backend;
import javax.inject.Named;

@Named
@Backend
public class AdminAlerts {

    public void alertAdmins(Throwable e) {
        System.out.println("alertAdmins " + e);
    }

    public void alertAdmins(String s) {
        System.out.println(s);
    }

}
