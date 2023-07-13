package local.atteam.iso_backup.ios_backup.service;

import com.jcraft.jsch.JSchException;
import local.atteam.iso_backup.ios_backup.dao.BackupRepository;
import local.atteam.iso_backup.ios_backup.dao.DeviceRepository;
import local.atteam.iso_backup.ios_backup.entity.Backup;
import local.atteam.iso_backup.ios_backup.entity.Device;
import local.atteam.iso_backup.ios_backup.exception.DeviceExistsException;
import local.atteam.iso_backup.ios_backup.exception.DeviceNotFoundException;
import local.atteam.iso_backup.ios_backup.message.DeviceStatusMessage;
import local.atteam.iso_backup.ios_backup.utility.SSHCommand;
import local.atteam.iso_backup.ios_backup.utility.SSHSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {


    private DeviceRepository deviceRepository;
    private BackupRepository backupRepository;

//    @Autowired
//    public DeviceServiceImpl(DeviceRepository deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, BackupRepository backupRepository) {
        this.deviceRepository = deviceRepository;
        this.backupRepository = backupRepository;
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Backup> findAllBackups() {
        return backupRepository.findAll();
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
            throw new DeviceNotFoundException("Device not found - ID: " + id);
        }
        return device;
    }

    @Override
    public Device findDeviceByName(String name) {
        return deviceRepository.findDeviceByName(name);
    }

    @Override
    public Device findDeviceByIp(String ip) {
        return deviceRepository.findDeviceByIp(ip);
    }

    @Transactional
    @Override
    public Device saveDevice(Device device) {
        String ip = device.getIp();
        Device existingDevice = findDeviceByIp(ip);
        if (existingDevice != null) {
            throw new DeviceExistsException("Device with IP " + ip + " already exists.");
        }
        return deviceRepository.save(device);
    }

    @Transactional
    @Override
    public Backup saveBackup(Backup backup) {
        return backupRepository.save(backup);
    }

    @Override
    public List<Backup> findAllByDeviceId(int id) {
        return backupRepository.findAllByDeviceId(id);
    }

    @Override
    public List<Backup> findAllBackupsOfADevice(Device device) {
        Device deviceByIp = findDeviceByIp(device.getIp());
        return findAllByDeviceId(deviceByIp.getId());
    }

    @Transactional
    @Override
    public void deleteDeviceById(int id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public Device deleteDeviceByIp(String ip) {
        Device device = findDeviceByIp(ip);
        if (device == null) {
            throw new DeviceNotFoundException("Device not found - IP: " + ip);
        }
        deviceRepository.deleteById(device.getId());
        return device;
    }

    @Override
    public DeviceStatusMessage checkIfDeviceReachable(Device device) throws JSchException {
        SSHSession session = new SSHSession(device);
        if (session.getSession() != null) {
            session.closeSession();
            return new DeviceStatusMessage(HttpStatus.OK.value(), "Connection Successful", returnSimpleDateFormat());
        }
        return null;
    }

    @Override
    public Backup createBackup(Device device) throws JSchException {

        Backup backup = new Backup();
        Device device1 = findDeviceByName(device.getName());
        System.out.println(device1);

        SSHSession session = new SSHSession(device1);
        if (session.getSession() != null) {
            SSHCommand sshCommand = new SSHCommand(session, "show runn");
            String res = sshCommand.executeCommand();
            backup.setDeviceId(device1.getId());
            backup.setTimeStamp(returnSimpleDateFormat());
            backup.setPayload(res);
            session.closeSession();
        }
        System.out.println(backup.getPayload());
        return backupRepository.save(backup);
    }


    private String returnSimpleDateFormat() {
        String simpleDateFormatPattern = "MM-dd-yyyy_HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(simpleDateFormatPattern);
        return simpleDateFormat.format(new Date());
    }


}