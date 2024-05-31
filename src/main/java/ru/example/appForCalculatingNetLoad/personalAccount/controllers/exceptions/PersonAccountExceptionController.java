package ru.example.appForCalculatingNetLoad.personalAccount.controllers.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.PasswordsDontMatchException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.UserNotFoundException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.WrongOldPasswordException;

@ControllerAdvice
public class PersonAccountExceptionController {

    @ExceptionHandler({
            PasswordsDontMatchException.class,
            WrongOldPasswordException.class,
            UserNotFoundException.class
    })
    public String handlePasswordsDontMatchException(
            PasswordsDontMatchException ex) {
        return "redirect:/personal-account/update-password?message=" + ex.getMessage();
    }
}
