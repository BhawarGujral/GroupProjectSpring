package com.example.Home_User_Service.controller;

import com.example.Home_User_Service.model.Device;
import com.example.Home_User_Service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // Default mapping for /devices - Redirect to device management page
    @GetMapping
    public String defaultMapping() {
        return "redirect:/devices/device-management"; // Redirect to /devices/device-management
    }

    // Mapping for device management HTML page
    @GetMapping("/device-management")
    public String showDeviceManagementPage() {
        return "DeviceManagement"; // HTML file located in src/main/resources/templates
    }

    // REST API: Add a new device
    @PostMapping("/add")
    @ResponseBody
    public Device addDevice(@RequestBody Device device) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        device.setOwner(username); // Set the owner as the logged-in user
        return deviceService.addDevice(device);
    }

    // REST API: Get devices owned by the logged-in user
    @GetMapping("/my-devices")
    @ResponseBody
    public List<Device> getMyDevices() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return deviceService.getDevicesByOwner(username);
    }

    // REST API: Get all devices
    @GetMapping("/all")
    @ResponseBody
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // REST API: Update the status of a device
    @PutMapping("/update-status/{id}")
    @ResponseBody
    public String updateDeviceStatus(@PathVariable Long id, @RequestParam String status) throws MessagingException {
        deviceService.updateDeviceStatus(id, status);
        return "Device status updated and notification sent.";

    }

    // REST API: Delete a device
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteDevice(@PathVariable Long id) throws MessagingException {
        deviceService.deleteDevice(id);
        return "Device deleted and notification sent.";

    }
}
