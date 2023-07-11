package local.atteam.iso_backup.ios_backup.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import local.atteam.iso_backup.ios_backup.entity.Device;
import org.springframework.stereotype.Component;

@Component
public class SSHSession {
    String userName;
    String password;
    String hostIp;
    int port;
    Session session;

    public SSHSession() {
    }

    public SSHSession(String userName, String password, String hostIp, int port) throws JSchException {
        this.userName = userName;
        this.password = password;
        this.hostIp = hostIp;
        this.port = port;
        this.session = createSession();
    }

    public SSHSession(Device device) throws JSchException {
        this.userName = device.getUser();
        this.password = device.getPassword();
        this.hostIp = device.getIp();
        this.port = device.getPort();
        this.session = createSession();

    }

    private Session createSession() throws JSchException {

            JSch jSch = new JSch();
            Session session = jSch.getSession(userName, hostIp, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(1500);
            session.connect();
            System.out.println(session);
            return session;

    }

    public void closeSession() {
        if (session != null) {
            session.disconnect();
        }


    }

    public Session getSession() {
        return session;
    }
}