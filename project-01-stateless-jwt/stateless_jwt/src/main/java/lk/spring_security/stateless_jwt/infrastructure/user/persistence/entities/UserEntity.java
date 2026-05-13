package lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lk.spring_security.stateless_jwt.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Email
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
