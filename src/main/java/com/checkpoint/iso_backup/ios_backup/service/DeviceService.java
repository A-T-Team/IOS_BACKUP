package com.checkpoint.iso_backup.ios_backup.service;

import com.checkpoint.iso_backup.ios_backup.entity.Device;

import java.util.List;

public interface DeviceService {
    List<Device> findAll();

    List<Device> findAllByVendor(String vendor);

    Device findDeviceById(int id);

    Device saveDevice(Device device);

    void deleteDeviceById(int id);
}