package com.luv2code.demo.demo.modal.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class SignupRequest {

    @NotBlank(message = "Cannot be empty !!!")
    private String username;

    @NotBlank(message = "Cannot be empty !!!")
    @Pattern(regexp = "^.+@([a-z]+\\.)+[a-z]{2,4}$", message = "Wrong Format")
    private String email;

    @NotBlank(message = "Cannot be empty !!!")
    private String password;

    public SignupRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
