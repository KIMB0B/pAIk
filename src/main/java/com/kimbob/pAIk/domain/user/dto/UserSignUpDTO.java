package com.kimbob.pAIk.domain.user.dto;

import com.kimbob.pAIk.domain.user.entity.Role;
import com.kimbob.pAIk.domain.user.entity.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Birth year is required")
    @Min(value = 1900, message = "Birth year must be after 1900")
    @Max(value = 2100, message = "Birth year must be before 2100")
    private Integer birthYear;

    @NotNull(message = "Birth month is required")
    @Min(value = 1, message = "Birth month must be between 1 and 12")
    @Max(value = 12, message = "Birth month must be between 1 and 12")
    private Integer birthMonth;

    @NotNull(message = "Birth date is required")
    @Min(value = 1, message = "Birth date must be valid")
    @Max(value = 31, message = "Birth date must be valid")
    private Integer birthDate;

    public LocalDate getBirthday() {
        return LocalDate.of(birthYear, birthMonth, birthDate);
    }

    public User toUser(Set<Role> roles) {
        User user = new User();
        user.setName(this.name);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setBirthday(this.getBirthday());
        user.setRoles(roles);
        return user;
    }
}
