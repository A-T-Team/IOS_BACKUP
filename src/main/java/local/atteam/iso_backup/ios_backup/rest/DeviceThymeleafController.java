package local.atteam.iso_backup.ios_backup.rest;


import local.atteam.iso_backup.ios_backup.entity.Device;
import local.atteam.iso_backup.ios_backup.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DeviceThymeleafController {

    private DeviceService deviceService;

    public DeviceThymeleafController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/list")
    public String listDevice(Model theModel) {
        List<Device> devices = deviceService.findAll();


        // add to the spring model
        theModel.addAttribute("devices", devices);

        return "list-devices";
    }


}