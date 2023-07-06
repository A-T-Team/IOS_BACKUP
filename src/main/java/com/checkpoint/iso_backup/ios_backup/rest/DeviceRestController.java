package com.checkpoint.iso_backup.ios_backup.rest;

import com.checkpoint.iso_backup.ios_backup.entity.Device;
import com.checkpoint.iso_backup.ios_backup.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/devices/vendor/{vendor}")
//    public List<Device> findAllDevicesByVendor(@PathVariable String vendor) {
//        return deviceService.findAllDevicesByVendor(vendor);
//    }

    @GetMapping("/devices/id/{id}")
    public Device findDeviceById(@PathVariable int id) {
        Device device = deviceService.findDeviceById(id);
        if (device == null) {
            throw new RuntimeException("Device not found: " + id);
        }
        return device;
    }

    @PostMapping("/devices")
    public Device addDevice(@RequestBody Device device) {
        device.setId(0);
        return deviceService.saveDevice(device);
    }

    @PutMapping("/devices")
    public Device updateDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device);

    }

    @DeleteMapping("/devices/{id}")
    public void deleteDevice(@PathVariable int id) {
        Device device = deviceService.findDeviceById(id);
        if (device == null) {
            throw new RuntimeException("Device not found: " + id);
        }
        deviceService.deleteDeviceById(id);



    }

}