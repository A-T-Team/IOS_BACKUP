package com.checkpoint.iso_backup.ios_backup.rest;

import com.checkpoint.iso_backup.ios_backup.entity.Device;
import com.checkpoint.iso_backup.ios_backup.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceRestController {
    private DeviceService deviceService;


    @Autowired
    public DeviceRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    public List<Device> findAll() {
        return deviceService.findAll();
    }

    @GetMapping("/devices/{vendor}")
    public List<Device> findAllDevicesByVendor(@PathVariable String vendor) {
        return deviceService.findAllDevicesByVendor(vendor);
    }
}