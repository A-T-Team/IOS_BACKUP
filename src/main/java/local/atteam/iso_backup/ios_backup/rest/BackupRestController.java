package local.atteam.iso_backup.ios_backup.rest;

import local.atteam.iso_backup.ios_backup.entity.Backup;
import local.atteam.iso_backup.ios_backup.entity.Device;
import local.atteam.iso_backup.ios_backup.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BackupRestController {
    private DeviceService deviceService;

    @Autowired
    public BackupRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/backups")
    public List<Backup> findAll() {
        return deviceService.findAllBackups();
    }

    @GetMapping("backups/device")
    public List<Backup> findAllBackupsOfADevice(@RequestBody Device device) {
        return deviceService.findAllBackupsOfADevice(device);
    }
}