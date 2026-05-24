package lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities;

import jakarta.persistence.*;
import lk.spring_security.stateless_jwt.domain.models.Role;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.util.List;

@Entity
@Table(name = "users")
@SoftDelete(columnName = "is_Deleted")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;
}
