package local.atteam.iso_backup.ios_backup.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity

@Table(name = "pool")
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    int id;

    @Column(name = "name")
    String name;


    @Column(name = "description")
    String description;


    @OneToMany(mappedBy = "pool",
            cascade = {CascadeType.ALL})
    List<Device> devices;

    public Pool() {
    }

    public Pool(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addDeviceToAPool(Device device) {
        if (device == null) {
            devices = new ArrayList<>();
        }
        devices.add(device);
        device.setPool(this);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Pool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    void addDevice(Device device) {
        if (devices == null) {
            devices = new ArrayList<>();
        }
        devices.add(device);
        device.setPool(this);

    }
}