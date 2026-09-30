package lk.spring_security.method_level_security_global_security_exceptions.domain.repositories;

public interface IdentityManager {
    //authenticate user
    void authenticate(String email, String password);
}
