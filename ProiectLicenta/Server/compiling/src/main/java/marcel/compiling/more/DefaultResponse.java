package marcel.compiling.more;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class DefaultResponse {
    public static final ResponseEntity BAD_REQUEST_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    public static final ResponseEntity NOT_FOUND_RESPONSE = ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    public static final ResponseEntity UNAUTHORIZED_RESPONSE = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
}
