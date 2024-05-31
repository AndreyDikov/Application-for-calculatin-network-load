package ru.example.appForCalculatingNetLoad.personalAccount.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WrongOldPasswordException extends Exception {

    private final String message;
}
