package local.atteam.iso_backup.ios_backup.rest;

import com.jcraft.jsch.JSchException;
import local.atteam.iso_backup.ios_backup.dto.CreateDeviceDTO;
import local.atteam.iso_backup.ios_backup.dto.DeviceDTO;
import local.atteam.iso_backup.ios_backup.entity.Backup;
import local.atteam.iso_backup.ios_backup.entity.Device;
import local.atteam.iso_backup.ios_backup.message.DeviceStatusMessage;
import local.atteam.iso_backup.ios_backup.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DeviceRestController {
    private final DeviceService deviceService;


    @Autowired
    public DeviceRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    public List<DeviceDTO> findAll() {
        return deviceService.findAll();
    }

    @GetMapping("/devices/vendor/{vendor}")
    public List<Device> findAllDevicesByVendor(@PathVariable String vendor) {
        return deviceService.findAllByVendor(vendor);
    }

    @GetMapping("/devices/id/{id}")
    public DeviceDTO findDeviceById(@PathVariable int id) {
        DeviceDTO device = deviceService.findDeviceById(id);
        if (device == null) {
            throw new RuntimeException("Device not found: " + id);
        }
        return device;
    }

    @GetMapping("/devices/name/{name}")
    public Device findDeviceByName(@PathVariable String name) {
        return deviceService.findDeviceByName(name);
    }

    @GetMapping("/devices/ip/{ip}")
    public Device findDeviceByIp(@PathVariable String ip) {
        return deviceService.findDeviceByIp(ip);
    }

    @PostMapping("/devices")
    public DeviceDTO addDevice(@RequestBody CreateDeviceDTO device) {
//        device.;
        return deviceService.saveDevice(device);
    }

//    @PutMapping("/devices")
//    public Device updateDevice(@RequestBody Device device) {
//        return deviceService.saveDevice(device);
//
//    }

//    @DeleteMapping("/devices/{id}")
//    public void deleteDevice(@PathVariable int id) {
//        Device device = deviceService.findDeviceById(id);
//        if (device == null) {
//            throw new DeviceNotFoundException("Device not found: " + id);
//        }
//        deviceService.deleteDeviceById(id);
//
//    }

    @DeleteMapping("/devices/delete/{ip}")
    public Device deleteDeviceByIp(@PathVariable String ip) {
        return deviceService.deleteDeviceByIp(ip);

    }


    @PostMapping("devices/availability")
    public DeviceStatusMessage checkIfDeviceReachable(@RequestBody Device device) throws JSchException {
        return deviceService.checkIfDeviceReachable(device);
    }


    @PostMapping("devices/backup")
    public Backup createBackup(@RequestBody Device device) throws JSchException {
        return deviceService.createBackup(device);
    }

    @GetMapping("/devices/pool/{pool}")
    public List<DeviceDTO> findAllDevicesByPool(@PathVariable Integer pool) {
        return deviceService.findAllDevicesByPool(pool);
    }
}