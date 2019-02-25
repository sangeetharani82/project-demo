package org.launchcode.SpringFilterBasedAuth.Controller;

import org.launchcode.SpringFilterBasedAuth.models.Recipe;
import org.launchcode.SpringFilterBasedAuth.models.User;
import org.launchcode.SpringFilterBasedAuth.models.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("recipe")
public class RecipeController extends AbstractController {

    @Autowired
    RecipeDao recipeDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "All recipes");
        return "recipe/index";
    }

    @RequestMapping(value = "userIndex/{uid}", method = RequestMethod.GET)
    public String userIndex(Model model, @PathVariable int uid){
        User user = userDao.findOne(uid);
        model.addAttribute("title", user.getEmail());
        model.addAttribute("recipes", user.getRecipes());
        return "recipe/userIndex";
    }

    // add/create a recipe
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddRecipeForm(Model model, User user) {
        int uid = user.getUid();
        model.addAttribute("title", uid + " " + user.getEmail());
        model.addAttribute(new Recipe());
        return "recipe/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRecipeForm(Model model, @ModelAttribute @Valid Recipe newRecipe,
                                       Errors errors, User user) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a recipe" + newRecipe.getUser().getEmail());
            return "recipe/add";
        }
        newRecipe.setUser(user);
        recipeDao.save(newRecipe);
        model.addAttribute("message", "Recipe created for " + user.getEmail());
        model.addAttribute("recipes", user.getRecipes());
        return "recipe/userIndex";
        // return "redirect:single/"+newRecipe.getId();
    }
}
