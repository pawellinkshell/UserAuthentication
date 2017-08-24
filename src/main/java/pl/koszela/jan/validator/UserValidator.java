package pl.koszela.jan.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.koszela.jan.model.User;
import pl.koszela.jan.service.UserService;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
@Component
public class UserValidator implements Validator {

  @Autowired
  private UserService userService;

  @Override
  public boolean supports(Class<?> aClass) {
    return User.class.equals(aClass);
  }

  @Override
  public void validate(Object validateUser, Errors errors) {

    User user = (User) validateUser;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
    if (isUsernameLengthValid(user)) {
      errors.rejectValue("username", "Size.userForm.username");
    }
    if (isUserExists(user)) {
      errors.rejectValue("username", "Duplicate.userForm.username");
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
    if (isPasswordLengthValid(user)) {
      errors.rejectValue("password", "Size.userForm.password");
    }
    if (isPasswordConfirmationValid(user)) {
      errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
    }
  }

  private boolean isPasswordConfirmationValid(User user) {
    return !user.getPasswordConfirm().equals(user.getPassword());
  }

  private boolean isPasswordLengthValid(User user) {
    return user.getPassword().length() < 8 || user.getPassword().length() > 32;
  }

  private boolean isUserExists(User user) {
    return userService.findByUsername(user.getUsername()) != null;
  }

  private boolean isUsernameLengthValid(User user) {
    return user.getUsername().length() < 6 || user.getUsername().length() > 32;
  }
}
