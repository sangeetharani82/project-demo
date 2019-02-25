package org.launchcode.SpringFilterBasedAuth.models.forms;
import javax.validation.constraints.NotNull;

public class RegisterForm extends LoginForm{

    @NotNull
    private String name;

    @NotNull(message = "Passwords to not match")
    private String verify;

    @Override
    public void setPassword(String password){
        super.setPassword(password);
        checkPasswordForRegistration();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
        checkPasswordForRegistration();
    }
    private void checkPasswordForRegistration() {
        if (!getPassword().equals(verify)) {
            verify = null;
        }
    }
}
