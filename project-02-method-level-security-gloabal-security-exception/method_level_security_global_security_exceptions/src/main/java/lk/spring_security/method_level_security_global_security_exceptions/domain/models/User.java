package lk.spring_security.method_level_security_global_security_exceptions.domain.models;


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

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

}
