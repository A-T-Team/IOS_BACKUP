package com.checkpoint.iso_backup.ios_backup;


import org.springframework.stereotype.Component;

@Component
public class DellSwitch implements Switch {

    public DellSwitch() {
        System.out.println("In constr:" + getClass().getSimpleName());
    }

    @Override
    public String getSwitchVendor() {
        return "I am a Dell Switch";
    }
}