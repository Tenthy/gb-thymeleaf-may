package ru.kmetha.gbapimay.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @JsonIgnore
    private long id;

    @NotBlank
    @Size(min = 3, message = "Username lenght must be grater than 3 symbols")
    private String username;
    @NotBlank
    @Size(min = 8, message = "Password lenght must be grater than 8 symbols")
    private String password;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 5)
    private String phone;
}
