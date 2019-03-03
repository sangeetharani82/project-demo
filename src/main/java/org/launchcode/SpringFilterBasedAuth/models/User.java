package org.launchcode.SpringFilterBasedAuth.models;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{

    @NotNull
    private String email;

    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToMany
    @JoinColumn(name = "user_uid")
    private List<Recipe> recipes = new ArrayList<>();

    public User() {
    }

    public User(String email, String pwHash) {
        this.email = email;
        this.pwHash = hashPassword(pwHash);
    }

    public String getEmail() {
        return email;
    }

    private static String hashPassword(String password){
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
