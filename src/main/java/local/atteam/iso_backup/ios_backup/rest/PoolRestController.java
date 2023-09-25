package local.atteam.iso_backup.ios_backup.rest;

import local.atteam.iso_backup.ios_backup.dto.PoolDTO;
import local.atteam.iso_backup.ios_backup.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PoolRestController {
    private final DeviceService deviceService;


    @Autowired
    public PoolRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/pools")
    public PoolDTO addPool(@RequestBody PoolDTO pool) {
        return deviceService.savePool(pool);
    }

    @GetMapping("/pools")
    public List<PoolDTO> findAll() {
        return deviceService.findAllPools();
    }

//    Add number of the devices


}