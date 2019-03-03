package org.launchcode.SpringFilterBasedAuth.Controller;

import org.launchcode.SpringFilterBasedAuth.models.User;
import org.launchcode.SpringFilterBasedAuth.models.forms.LoginForm;
import org.launchcode.SpringFilterBasedAuth.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthenticationController extends AbstractController{

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "Users list");
        model.addAttribute("users", userDao.findAll());
        return "user/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model) {
        model.addAttribute(new RegisterForm());
        model.addAttribute("title", "Sign-up here");
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegisterForm(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request,
                           Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Sign-up here");
            model.addAttribute("error", "One or more fields invalid");
            return "user/register";
        }

        User existingUser = userDao.findByEmail(form.getEmail());

        if (existingUser != null){
            model.addAttribute("error", "Email already exists. Try another");
            return "user/register";
        }

        User newUser = new User(form.getEmail(), form.getPassword());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        model.addAttribute("title", form.getName());
        model.addAttribute("recipes", newUser.getRecipes());
        return "redirect:/recipe/userIndex/" + newUser.getUid();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginForm());
        model.addAttribute("title", "Log In");
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLoginForm(Model model, @ModelAttribute @Valid LoginForm form, Errors errors,
                                   HttpServletRequest request){

        if (errors.hasErrors()){
            model.addAttribute("title", "Log In");
            model.addAttribute("error", "One or more fields are invalid");
            return "user/login";
        }

        User theUser = userDao.findByEmail(form.getEmail());
        String password = form.getPassword();

        if (theUser == null){
            model.addAttribute("title", "Log In");
            model.addAttribute("error", "Email does not exist");
            return "user/login";
        }
        if (!theUser.isMatchingPassword(password)){
            model.addAttribute("title", "Log In");
            model.addAttribute("error", "Password don't match");
            return "user/login";
        }

        setUserInSession(request.getSession(), theUser);
        model.addAttribute("recipes", theUser.getRecipes());
        model.addAttribute("title", theUser.getEmail());

        return "redirect:/recipe/userIndex/" + theUser.getUid();
    }

    @RequestMapping(value="/view/{id}", method = RequestMethod.GET)
    public String viewUser(@PathVariable int id, Model model){
        User user = userDao.findOne(id);
        model.addAttribute("title", user.getEmail());
        model.addAttribute("user", user.getEmail());
        return "user/view";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request){
        request.getSession().invalidate();
        model.addAttribute("message", "Logged out successfully!");
        return "redirect:user/login";
    }

}
