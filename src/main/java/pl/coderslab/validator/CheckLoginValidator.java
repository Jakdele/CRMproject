package pl.coderslab.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckLoginValidator implements ConstraintValidator<CheckLogin, String> {
    @Autowired
    UserRepository userRepository;
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpSession session = attr.getRequest().getSession();

    public void initialize(CheckLogin constraint) {
    }

    public boolean isValid(String login, ConstraintValidatorContext context) {
        User user = userRepository.findByLogin(login);

        if (user != null) {
            return false;
        }
        return true;
    }

}
