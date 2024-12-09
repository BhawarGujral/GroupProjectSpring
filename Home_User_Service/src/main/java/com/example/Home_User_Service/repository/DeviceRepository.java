package com.example.Home_User_Service.repository;

import com.example.Home_User_Service.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByOwner(String owner); // Find devices by the owner's username
}
