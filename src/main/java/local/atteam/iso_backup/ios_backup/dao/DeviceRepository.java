package local.atteam.iso_backup.ios_backup.dao;

import local.atteam.iso_backup.ios_backup.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    public List<Device> findAllByVendor(String vendor);

    public Device findDeviceByName(String name);

}