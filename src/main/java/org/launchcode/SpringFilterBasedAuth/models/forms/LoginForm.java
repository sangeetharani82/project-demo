package org.launchcode.SpringFilterBasedAuth.models.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginForm {

    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp = "(\\S){4,20}", message = "Password must have 4-20 non-whitespace characters")
    private String password;

    public LoginForm() {
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
