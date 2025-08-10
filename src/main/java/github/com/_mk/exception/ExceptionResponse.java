package github.com._mk.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
