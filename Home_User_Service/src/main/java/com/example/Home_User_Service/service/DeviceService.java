package com.example.Home_User_Service.service;

import com.example.Home_User_Service.model.Device;
import com.example.Home_User_Service.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private NotificationService notificationService;

    // Add a new device and notify the user
    public Device addDevice(Device device) {
        Device savedDevice = deviceRepository.save(device);
        notificationService.createNotification(
                "Device '" + savedDevice.getName() + "' has been added.",
                savedDevice.getOwner()
        );
        return savedDevice;
    }

    // Get devices owned by a specific user
    public List<Device> getDevicesByOwner(String owner) {
        return deviceRepository.findByOwner(owner);
    }

    // Get all devices (Admin use case)
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // Update device status and notify the user
    public void updateDeviceStatus(Long id, String status) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + id));

        device.setStatus(status);
        deviceRepository.save(device);

        notificationService.createNotification(
                "Device '" + device.getName() + "' has been updated to status: " + status,
                device.getOwner()
        );
    }

    // Delete a device and notify the user
    public void deleteDevice(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + id));

        deviceRepository.delete(device);
        notificationService.createNotification(
                "Device '" + device.getName() + "' has been deleted.",
                device.getOwner()
        );
    }

    // Scheduled task to turn off devices at 11 PM every night
    @Scheduled(cron = "0 0 23 * * ?") // Run at 11 PM daily
    public void turnOffDevicesAtNight() {
        List<Device> devices = deviceRepository.findAll();

        for (Device device : devices) {
            if ("ON".equalsIgnoreCase(device.getStatus())) {
                device.setStatus("OFF");
                deviceRepository.save(device);
                notificationService.createNotification(
                        "Device '" + device.getName() + "' has been automatically turned OFF at 11 PM.",
                        device.getOwner()
                );
                System.out.println("Device " + device.getName() + " turned OFF at 11 PM.");
            }
        }
    }

    // Optional: 5-second scheduler (Commented Out)

//    @Scheduled(cron = "*/5 * * * * ?") // Run every 5 seconds (for testing)
//    public void testScheduler() {
//        System.out.println("Test scheduler executed at: " + System.currentTimeMillis());
//    }

}
