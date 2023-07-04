package com.checkpoint.iso_backup.ios_backup.configuration;


import com.checkpoint.iso_backup.ios_backup.HpSwitch;
import com.checkpoint.iso_backup.ios_backup.Switch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwitchConfig {
    @Bean("hp")
    public Switch hpSwitch() {
        return new HpSwitch();
    }

}