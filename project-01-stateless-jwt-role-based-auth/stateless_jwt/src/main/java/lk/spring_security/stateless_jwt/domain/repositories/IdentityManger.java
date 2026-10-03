package lk.spring_security.stateless_jwt.domain.repositories;

public interface IdentityManger {
    //authenticate username and paw
    void authenticateUser(String email, String password);
}
