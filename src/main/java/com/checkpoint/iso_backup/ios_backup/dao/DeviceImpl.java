//package com.checkpoint.iso_backup.ios_backup.dao;
//
//import com.checkpoint.iso_backup.ios_backup.entity.Device;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class DeviceImpl implements DeviceDAO {
//    private EntityManager entityManager;
//
//    @Autowired
//    public DeviceImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
////    TODO: Create a list of supported vendors.
//
//    @Override
//    public List<Device> findAll() {
//        TypedQuery<Device> typedQuery = entityManager.createQuery("from Device", Device.class);
//        return typedQuery.getResultList();
//    }
//
//    @Override
//    public List<Device> findAllDevicesByVendor(String vendor) {
//        TypedQuery<Device> typedQuery = entityManager.createQuery("FROM Device WHERE vendor=:vendor", Device.class);
//        typedQuery.setParameter("vendor", vendor);
//        return typedQuery.getResultList();
//    }
//
//    @Override
//    public Device findDeviceById(int id) {
//        return entityManager.find(Device.class, id);
//    }
//
//    @Override
//    public Device saveDevice(Device device) {
//        return entityManager.merge(device);
//    }
//
//    @Override
//    public Device deleteDeviceById(int id) {
//        Device deviceToDelete = entityManager.find(Device.class, id);
//        entityManager.remove(deviceToDelete);
//        return deviceToDelete;
//    }
//
//}