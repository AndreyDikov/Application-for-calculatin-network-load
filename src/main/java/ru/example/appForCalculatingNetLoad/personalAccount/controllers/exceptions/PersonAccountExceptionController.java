package ru.example.appForCalculatingNetLoad.personalAccount.controllers.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.PasswordsDontMatchException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.UserNotFoundException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.WrongOldPasswordException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@ControllerAdvice
public class PersonAccountExceptionController {

    @ExceptionHandler({
            PasswordsDontMatchException.class,
            WrongOldPasswordException.class,
            UserNotFoundException.class
    })
    public String handlePasswordsDontMatchException(
            Exception ex) throws UnsupportedEncodingException {
        return "redirect:/personal-account/update-password?message="
                + URLEncoder.encode(ex.getMessage(), "UTF-8");
    }
}
