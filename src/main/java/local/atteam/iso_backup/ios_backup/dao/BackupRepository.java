package local.atteam.iso_backup.ios_backup.dao;

import local.atteam.iso_backup.ios_backup.entity.Backup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BackupRepository extends JpaRepository<Backup, UUID> {
    public List<Backup> findAllByDeviceId(int id);

}