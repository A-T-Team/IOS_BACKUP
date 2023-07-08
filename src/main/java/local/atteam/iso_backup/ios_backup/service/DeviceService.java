package local.atteam.iso_backup.ios_backup.service;

import local.atteam.iso_backup.ios_backup.entity.Device;

import java.util.List;

public interface DeviceService {
    List<Device> findAll();

    List<Device> findAllByVendor(String vendor);

    Device findDeviceById(int id);

    Device findDeviceByName(String name);

    Device saveDevice(Device device);

    void deleteDeviceById(int id);
}