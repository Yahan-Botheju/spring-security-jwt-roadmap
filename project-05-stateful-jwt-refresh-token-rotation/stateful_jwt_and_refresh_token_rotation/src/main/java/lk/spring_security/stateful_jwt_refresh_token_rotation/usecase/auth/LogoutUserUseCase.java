package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

public interface LogoutUserUseCase {
    //logout user
    void logout(String refreshToken);
}
