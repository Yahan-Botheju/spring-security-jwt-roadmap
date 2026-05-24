package lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

@Entity
@Table(name = "tasks")
@SoftDelete(columnName = "is_deleted")
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

    @NotNull(message = "Status cannot be empty")
    private Boolean completed = false ;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
