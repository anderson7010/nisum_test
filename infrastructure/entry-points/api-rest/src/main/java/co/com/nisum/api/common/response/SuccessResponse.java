package co.com.nisum.api.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuccessResponse<T> {
    private T data;
    private String message;
}
