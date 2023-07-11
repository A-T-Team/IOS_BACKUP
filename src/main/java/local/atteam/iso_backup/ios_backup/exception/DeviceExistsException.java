package local.atteam.iso_backup.ios_backup.exception;

public class DeviceExistsException extends RuntimeException {
    public DeviceExistsException(String message) {
        super(message);
    }

    public DeviceExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceExistsException(Throwable cause) {
        super(cause);
    }
}