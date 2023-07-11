package local.atteam.iso_backup.ios_backup.utility;

import com.jcraft.jsch.JSchException;

public class SSHTest {
    public static void main(String[] args) throws JSchException {
        SSHSession sshSession = new SSHSession("admin", "P@ssw0rd", "192.168.11.10", 22);


        SSHCommand sshCommand = new SSHCommand(sshSession, "show runn");
        String ifconfig = sshCommand.executeCommand();
        System.out.println(ifconfig);
        sshSession.closeSession();


    }
}