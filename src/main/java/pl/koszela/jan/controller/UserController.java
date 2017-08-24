package pl.koszela.jan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.koszela.jan.model.User;
import pl.koszela.jan.service.SecurityService;
import pl.koszela.jan.service.UserService;
import pl.koszela.jan.validator.UserValidator;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
@Controller
public class UserController {

  public static final String REGISTRATION_VIEW = "registration";
  public static final String LOGIN_VIEW = "login";
  public static final String WELCOME_VIEW = "welcome";

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private UserValidator userValidator;

  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String registrationPage(Model model) {
    model.addAttribute("userForm", new User());

    return REGISTRATION_VIEW;
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String registrationPage(@ModelAttribute("userForm") User userForm,
      BindingResult bindingResult, Model model) {
    userValidator.validate(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      return REGISTRATION_VIEW;
    }

    userService.save(userForm);

    securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

    return "redirect:/welcome";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginPage(Model model, String error, String logout) {
    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
    }

    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
    }

    return LOGIN_VIEW;
  }

  @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
  public String welcome(Model model) {
    return WELCOME_VIEW;
  }
}
