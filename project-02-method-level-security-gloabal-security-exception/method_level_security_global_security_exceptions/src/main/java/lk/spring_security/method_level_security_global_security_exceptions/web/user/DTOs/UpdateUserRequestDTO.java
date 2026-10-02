package lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO {
    @Email(message = "Please provide a correct email")
    @NotBlank(message = "Email cannot be empty")
    String email;
}
