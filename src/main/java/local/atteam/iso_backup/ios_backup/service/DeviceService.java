package local.atteam.iso_backup.ios_backup.service;

import com.jcraft.jsch.JSchException;
import local.atteam.iso_backup.ios_backup.entity.Backup;
import local.atteam.iso_backup.ios_backup.entity.Device;
import local.atteam.iso_backup.ios_backup.message.DeviceStatusMessage;

import java.util.List;

public interface DeviceService {
    List<Device> findAll();

    List<Backup> findAllBackups();

    List<Device> findAllByVendor(String vendor);

    Device findDeviceById(int id);

    Device findDeviceByName(String name);

    Device findDeviceByIp(String ip);

    Device saveDevice(Device device);

    Backup saveBackup(Backup backup);

    List<Backup> findAllByDeviceId(int id);

    List<Backup> findAllBackupsOfADevice(Device device);

    void deleteDeviceById(int id);

    Device deleteDeviceByIp(String ip);

    DeviceStatusMessage checkIfDeviceReachable(Device device) throws JSchException;

    Backup createBackup(Device device) throws JSchException;

}