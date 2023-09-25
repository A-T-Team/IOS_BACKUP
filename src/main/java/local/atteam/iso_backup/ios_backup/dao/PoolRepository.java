package local.atteam.iso_backup.ios_backup.dao;

import local.atteam.iso_backup.ios_backup.entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<Pool, Integer> {
}