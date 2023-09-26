package local.atteam.iso_backup.ios_backup.service;

import com.jcraft.jsch.JSchException;
import local.atteam.iso_backup.ios_backup.dao.BackupRepository;
import local.atteam.iso_backup.ios_backup.dao.DeviceRepository;
import local.atteam.iso_backup.ios_backup.dao.PoolRepository;
import local.atteam.iso_backup.ios_backup.dto.CreateDeviceDTO;
import local.atteam.iso_backup.ios_backup.dto.DeviceDTO;
import local.atteam.iso_backup.ios_backup.dto.PoolDTO;
import local.atteam.iso_backup.ios_backup.entity.Backup;
import local.atteam.iso_backup.ios_backup.entity.Device;
import local.atteam.iso_backup.ios_backup.entity.Pool;
import local.atteam.iso_backup.ios_backup.exception.DeviceExistsException;
import local.atteam.iso_backup.ios_backup.exception.DeviceNotFoundException;
import local.atteam.iso_backup.ios_backup.message.DeviceStatusMessage;
import local.atteam.iso_backup.ios_backup.utility.SSHCommand;
import local.atteam.iso_backup.ios_backup.utility.SSHSession;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {


    private final DeviceRepository deviceRepository;
    private final BackupRepository backupRepository;
    private final PoolRepository poolRepository;
    final ModelMapper modelMapper;


    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, BackupRepository backupRepository, PoolRepository poolRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.backupRepository = backupRepository;
        this.poolRepository = poolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DeviceDTO> findAll() {
        return deviceRepository.findAll().stream()
                .map(d -> modelMapper.map(d,DeviceDTO.class))
                .collect(Collectors.toList());
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
    public DeviceDTO findDeviceById(int id) {
        Optional<Device> res = deviceRepository.findById(id);
        Device device = null;
        if (res.isPresent()) {
            device = res.get();
        } else {
            throw new DeviceNotFoundException("Device not found - ID: " + id);
        }
        return modelMapper.map(device, DeviceDTO.class);
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
    public DeviceDTO saveDevice(CreateDeviceDTO device) {
        System.out.println(device.getUser());
        String ip = device.getIp();
        Device existingDevice = findDeviceByIp(ip);
        if (existingDevice != null) {
            throw new DeviceExistsException("Device with IP " + ip + " already exists.");
        }
        Device newDevice = modelMapper.map(device, Device.class);
        deviceRepository.save(newDevice);
        Optional<Pool> poolOptional = poolRepository.findById(device.getPoolID());
        Pool pool = null;
        if (poolOptional.isPresent()) {
            pool = poolOptional.get();
        }

        pool.addDeviceToAPool(newDevice);
        System.out.println(pool);
        return modelMapper.map(newDevice, DeviceDTO.class);
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

    @Transactional
    @Override
    public PoolDTO savePool(PoolDTO PoolDto) {
        Pool newPool = modelMapper.map(PoolDto, Pool.class);
        poolRepository.save(newPool);
        return modelMapper.map(newPool, PoolDTO.class);
    }


    public List<PoolDTO> findAllPools() {
        List<PoolDTO> poolDTOList = new ArrayList<PoolDTO>();
        return modelMapper.map(poolRepository.findAll(), new TypeToken<List<PoolDTO>>() {
        }.getType());
    }

    @Override
    public List<DeviceDTO> findAllDevicesByPool(int pool) {

        return deviceRepository.findDeviceByPoolId(pool).stream()
                .map(d -> modelMapper.map(d,DeviceDTO.class))
                .collect(Collectors.toList());
    }



    private String returnSimpleDateFormat() {
        String simpleDateFormatPattern = "MM-dd-yyyy_HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(simpleDateFormatPattern);
        return simpleDateFormat.format(new Date());
    }


}