package local.atteam.iso_backup.ios_backup.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHSession {
    String userName;
    String password;
    String hostIp;
    int port;
    Session session;

    public SSHSession(String userName, String password, String hostIp, int port) {
        this.userName = userName;
        this.password = password;
        this.hostIp = hostIp;
        this.port = port;
        this.session = createSession();
    }

    private Session createSession() {
        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession(userName, hostIp, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(1500);
            session.connect();
            System.out.println(session);
            return session;

        } catch (JSchException e) {
            e.printStackTrace();
        }
        return null;
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