package com.checkpoint.iso_backup.ios_backup.service;

import com.checkpoint.iso_backup.ios_backup.dao.DeviceDAO;
import com.checkpoint.iso_backup.ios_backup.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{

    private DeviceDAO deviceDAO;

    @Autowired
    public DeviceServiceImpl(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @Override
    public List<Device> findAll() {
        return deviceDAO.findAll();
    }

    @Override
    public List<Device> findAllDevicesByVendor(String vendor) {
        return deviceDAO.findAllDevicesByVendor(vendor);
    }

}