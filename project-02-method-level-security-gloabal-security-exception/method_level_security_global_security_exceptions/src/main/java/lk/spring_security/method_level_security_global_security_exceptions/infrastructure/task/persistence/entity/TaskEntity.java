package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tasks")
@SoftDelete(columnName = "is_deleted")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long takId;

    @NotBlank(message = "Task Title cannot be empty")
    private String tasKTitle;

    @NotBlank(message = "Task Description cannot be empty")
    private String taskDescription;

    private Boolean completed = false;

}
