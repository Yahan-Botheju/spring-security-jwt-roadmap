package lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import lombok.*;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @NotBlank(message = "Title cannot be empty")
    private String taskTitle;
    @NotBlank(message = "Description cannot be empty")
    private String taskDescription;

    private Boolean completed = false ;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
}
