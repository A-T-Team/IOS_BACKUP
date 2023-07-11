package local.atteam.iso_backup.ios_backup.message;

public class DeviceStatusMessage {
    int status;

    String message;

    String date;

    public DeviceStatusMessage() {
    }

    public DeviceStatusMessage(int status, String message, String date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}