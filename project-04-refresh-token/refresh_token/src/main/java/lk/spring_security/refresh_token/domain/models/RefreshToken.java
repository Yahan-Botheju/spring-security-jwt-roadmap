package lk.spring_security.refresh_token.domain.models;

import java.time.Instant;

public class RefreshToken {
    private Long id;
    private String token;
    private Instant expiryDate;
    private User user;

    public RefreshToken(Long id, String token, Instant expiryDate, User user) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public User getUser() {
        return user;
    }

    //check token is expired
    public boolean isExpired() {
        return this.expiryDate.isBefore(Instant.now());
    }
}
