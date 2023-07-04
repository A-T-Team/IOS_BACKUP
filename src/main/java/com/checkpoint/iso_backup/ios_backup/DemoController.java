package com.checkpoint.iso_backup.ios_backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Switch mySwitch;
//    private Switch anotherSwitch;


    @Autowired
    public DemoController(@Qualifier("hp") Switch mySwitch) {
        System.out.println("In constr:" + getClass().getSimpleName());
        this.mySwitch = mySwitch;
    }


    @GetMapping("/switchvendor")
    public String getSwitchName() {
        return mySwitch.getSwitchVendor();
    }
}