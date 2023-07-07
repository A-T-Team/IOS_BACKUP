package local.atteam.iso_backup.ios_backup.utility;

public class SSHTest {
    public static void main(String[] args) {
        SSHSession sshSession = new SSHSession("root", "P@ssw0rd", "192.168.11.133", 22);


        SSHCommand sshCommand = new SSHCommand(sshSession, "ifconfig");
        String ifconfig = sshCommand.executeCommand();
        System.out.println(ifconfig);
        sshSession.closeSession();

    }
}