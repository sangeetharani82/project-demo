package org.launchcode.SpringFilterBasedAuth.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 100, message = "Recipe Name needed")
    private String recipeName;

    @NotNull
    @Size(min=3, max = 3000000, message = "Direction field can't be empty")
    private String direction;

    @ManyToOne
    private User user;

    public Recipe() {
    }

    public Recipe(String recipeName, String direction, User user) {
        this.recipeName = recipeName;
        this.direction = direction;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
