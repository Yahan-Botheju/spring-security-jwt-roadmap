package lk.spring_security.refresh_token.domain.models;

import lk.spring_security.refresh_token.domain.enums.Role;

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

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    /* __FACTORY_METHOD__ */

    public static User createUser(String email, String password, Role role) {
        return new User(null, email, password, role);
    }



    /* __DOMAIN_LOGIC__ */
    public void setDefaultRole(){
        if(this.role == null || this.role == Role.ADMIN){
            throw new IllegalArgumentException("Cannot set role");
        }
        this.role = Role.USER;
    }
}
