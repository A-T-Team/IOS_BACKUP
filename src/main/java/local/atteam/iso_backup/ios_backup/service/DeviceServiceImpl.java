package local.atteam.iso_backup.ios_backup.service;

import local.atteam.iso_backup.ios_backup.dao.DeviceRepository;
import local.atteam.iso_backup.ios_backup.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService{

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> findAllByVendor(String vendor) {
        return deviceRepository.findAllByVendor(vendor);
    }

    @Override
    public Device findDeviceById(int id) {
        Optional<Device> res = deviceRepository.findById(id);
        Device device = null;
        if (res.isPresent()) {
            device = res.get();
        } else {
            throw new RuntimeException("Device not found: " + id);
        }
        return device;
    }

    @Transactional
    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Transactional
    @Override
    public void deleteDeviceById(int id) {
        deviceRepository.deleteById(id);
    }

}