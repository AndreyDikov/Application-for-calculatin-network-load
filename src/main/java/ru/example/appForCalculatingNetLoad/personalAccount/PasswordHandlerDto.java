package ru.example.appForCalculatingNetLoad.personalAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PasswordHandlerDto {

    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
}
