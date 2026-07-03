package lk.spring_security.refresh_token.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    private Long id;
    private String token;
    private Instant expiryDate;
    private User user;

    //check token is expired
    public boolean isExpired() {
        return this.expiryData.isBefore(Instant.now());
    }
}
