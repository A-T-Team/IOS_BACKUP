package local.atteam.iso_backup.ios_backup.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "backup")
public class Backup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "backup_id")
    UUID backupId;

    @Column(name = "device_id")
    int deviceId;

    @Column(name = "time_stamp")
    String timeStamp;

    @Column(name = "payload")
    String payload;


    public Backup() {
    }

    public Backup(int deviceId, String timeStamp, String payload) {
        this.deviceId = deviceId;
        this.timeStamp = timeStamp;
        this.payload = payload;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public UUID getBackupId() {
        return backupId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getPayload() {
        return payload;
    }
}