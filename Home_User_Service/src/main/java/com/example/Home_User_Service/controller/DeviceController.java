package com.example.Home_User_Service.controller;

import com.example.Home_User_Service.model.Device;
import com.example.Home_User_Service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // Redirect to device management page
    @GetMapping
    public String defaultMapping() {
        return "redirect:/devices/device-management";
    }

    // Show device management page
    @GetMapping("/device-management")
    public String showDeviceManagementPage() {
        return "DeviceManagement";
    }

    // Add a new device
    @PostMapping("/add")
    @ResponseBody
    public Device addDevice(@RequestBody Device device) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        device.setOwner(username); // Set logged-in user as the owner
        return deviceService.addDevice(device);
    }

    // Get devices owned by the logged-in user
    @GetMapping("/my-devices")
    @ResponseBody
    public List<Device> getMyDevices() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return deviceService.getDevicesByOwner(username);
    }

    // Get all devices (Admin use case)
    @GetMapping("/all")
    @ResponseBody
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // Update the status of a device
    @PutMapping("/update-status/{id}")
    public ResponseEntity<Void> updateDeviceStatus(
            @PathVariable Long id,
            @RequestParam("status") String status) {
        if (status == null || status.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        deviceService.updateDeviceStatus(id, status);
        return ResponseEntity.ok().build();
    }

    // Delete a device
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
