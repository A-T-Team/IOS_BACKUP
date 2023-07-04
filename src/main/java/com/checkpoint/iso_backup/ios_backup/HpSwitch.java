package com.checkpoint.iso_backup.ios_backup;

public class HpSwitch implements Switch {


    public HpSwitch() {
        System.out.println("I am a constructor of: " + getClass().getSimpleName());
    }

    @Override
    public String getSwitchVendor() {
        return "I am an HP Switch";
    }

}