package lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    @Email(message = "Please, enter valid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 6, message = "password should have at least 6 characters")
    private String password;
}
