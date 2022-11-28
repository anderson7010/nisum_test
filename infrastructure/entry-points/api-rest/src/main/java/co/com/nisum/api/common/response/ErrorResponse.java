package co.com.nisum.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "build")
public class ErrorResponse {
    private String message;
}
