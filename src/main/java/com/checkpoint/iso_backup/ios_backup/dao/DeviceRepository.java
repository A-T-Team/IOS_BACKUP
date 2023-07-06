package com.checkpoint.iso_backup.ios_backup.dao;

import com.checkpoint.iso_backup.ios_backup.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

}