package lk.spring_security.stateless_jwt.web.user.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileRequestDTO {
    private String newEmail;
}
