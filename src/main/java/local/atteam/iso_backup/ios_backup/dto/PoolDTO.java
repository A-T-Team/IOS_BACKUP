package local.atteam.iso_backup.ios_backup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PoolDTO {
    String name;
    String description;
    int id;
    int devicesAmount;



}