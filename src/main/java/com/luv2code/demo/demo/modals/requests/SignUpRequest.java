package com.luv2code.demo.demo.modals.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Cannot be empty !!!")
    private String username;

    @NotBlank(message = "Cannot be empty !!!")
    @Pattern(regexp = "^.+@([a-z]+\\.)+[a-z]{2,4}$", message = "Wrong Format")
    private String email;

    @NotBlank(message = "Cannot be empty !!!")
    private String password;

    @Override
    public String toString() {
        return "SignupRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
