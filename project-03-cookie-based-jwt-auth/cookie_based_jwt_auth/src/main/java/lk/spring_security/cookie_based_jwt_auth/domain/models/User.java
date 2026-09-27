package lk.spring_security.cookie_based_jwt_auth.domain.models;

public class User {
    private Long userId;
    private String email;
    private String password;
    private Role role;

    public User(Long userId, String email, String password, Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /* __GETTERS__ */

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }



}
