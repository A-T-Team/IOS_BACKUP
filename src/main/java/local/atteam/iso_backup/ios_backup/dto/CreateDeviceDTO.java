package local.atteam.iso_backup.ios_backup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDeviceDTO {

    String name;
    String vendor;
    String ip;
    Integer port;
    String user;
    String password;
    Integer poolID;

}