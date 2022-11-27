package co.com.nisum.api.auth.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
