package local.atteam.iso_backup.ios_backup.exception;

import com.jcraft.jsch.JSchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class DeviceRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DeviceErrorResponse> deviceNotFoundException(DeviceNotFoundException exception) {
        DeviceErrorResponse errorResponse = new DeviceErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(returnSimpleDateFormat());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DeviceErrorResponse> handleAnyException(Exception exception) {
        DeviceErrorResponse errorResponse = new DeviceErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(returnSimpleDateFormat());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<DeviceErrorResponse> deviceExistsException(DeviceExistsException exception) {
        DeviceErrorResponse errorResponse = new DeviceErrorResponse();
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(returnSimpleDateFormat());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler
    public ResponseEntity<DeviceErrorResponse> deviceS(JSchException exception) {
        DeviceErrorResponse errorResponse = new DeviceErrorResponse();
        errorResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(returnSimpleDateFormat());
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);

    }


    private String returnSimpleDateFormat() {
        String simpleDateFormatPattern = "MM-dd-yyyy_HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(simpleDateFormatPattern);
        return simpleDateFormat.format(new Date());
    }
}