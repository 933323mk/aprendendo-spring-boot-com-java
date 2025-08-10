package github.com._mk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {
    public RequiredObjectIsNullException() {
        super("NÃO É PERMITIDO PERSISTIR UM OBJETO NULO!");
    }
    public RequiredObjectIsNullException(String message) {
        super(message);
    }
}
