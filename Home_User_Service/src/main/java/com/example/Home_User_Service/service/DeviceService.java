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

    // Add a new device
    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    // Get devices by the owner's name
    public List<Device> getDevicesByOwner(String owner) {
        return deviceRepository.findByOwner(owner);
    }

    // Get all devices
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // Update device status and send alert
    public void updateDeviceStatus(Long id, String status) throws MessagingException {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + id));
        device.setStatus(status);
        deviceRepository.save(device);
      //  notificationService.sendDeviceStatusAlert(device.getName(), status);
    }

    // Delete a device and send error notification
    public void deleteDevice(Long id) throws MessagingException {
        deviceRepository.deleteById(id);
        //notificationService.sendErrorNotification("Device with ID " + id + " has been deleted.");
    }

    // Scheduled task to turn off devices at 11 PM every night
   // @Scheduled(cron = "0 0 23 * * ?") // Cron expression to run at 11 PM every day
    @Scheduled(cron = "0 * * * * ?") // This will run every minute

    public void turnOffDevicesAtNight() throws MessagingException {
        List<Device> devices = deviceRepository.findAll();

        for (Device device : devices) {
            if ("ON".equals(device.getStatus())) { // Check if the device is currently ON
                device.setStatus("OFF"); // Turn the device off
                deviceRepository.save(device);
               // notificationService.sendDeviceStatusAlert(device.getName(), "OFF"); // Send alert that the device was turned off
                System.out.println("Device " + device.getName() + " turned off at 11 PM.");
            }
        }
    }
}
