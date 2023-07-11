package local.atteam.iso_backup.ios_backup.utility;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;

import java.io.InputStream;

public class SSHCommand {
    SSHSession sshSession;
    String command;

    public SSHCommand(SSHSession sshSession, String command) {
        this.sshSession = sshSession;
        this.command = command;
    }

    public String executeCommand() {
        String res = "";
        try {
            String temp = null;
            Channel channel = sshSession.getSession().openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream inputStream = channel.getInputStream();
            channel.connect();
            byte[] byteObject = new byte[10240];
            do {
                while (inputStream.available() > 0) {
                    int readByte = inputStream.read(byteObject, 0, 1024);
                    if (readByte < 0)
                        break;
                    temp = new String(byteObject, 0, readByte);
                    res = res + temp;
                }

            } while (!channel.isClosed());
            channel.disconnect();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;


    }
}