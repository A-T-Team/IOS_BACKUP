package com.checkpoint.iso_backup.ios_backup;


import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CiscoSwitch implements Switch {

    public CiscoSwitch() {
        System.out.println("In constr:" + getClass().getSimpleName());
    }

//    @PostConstruct
//    public void startupStuff() {
//        System.out.println("Startup stuff for " + getClass().getSimpleName());
//
//    }
//
//    @PreDestroy
//    public void destroyStuff() {
//        System.out.println("Cleanup stuff for " + getClass().getSimpleName());
//    }


    @Override
    public String getSwitchVendor() {
        return "I am a Cisco Switch";
    }
}