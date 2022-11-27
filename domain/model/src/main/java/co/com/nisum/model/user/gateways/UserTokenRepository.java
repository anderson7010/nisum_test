package co.com.nisum.model.user.gateways;

public interface UserTokenRepository {
    String generateToken(String email);
}
