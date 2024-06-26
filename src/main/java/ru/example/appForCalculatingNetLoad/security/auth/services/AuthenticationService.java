package ru.example.appForCalculatingNetLoad.security.auth.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.CalculationRepository;
import ru.example.appForCalculatingNetLoad.security.auth.dto.AuthenticationRequest;
import ru.example.appForCalculatingNetLoad.security.auth.dto.AuthenticationResponse;
import ru.example.appForCalculatingNetLoad.security.auth.dto.RegisterRequest;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.enums.UserRole;
import ru.example.appForCalculatingNetLoad.security.securityUsers.services.SecurityUserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SecurityUserService securityUserService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CalculationRepository calculationRepository;

    /**
     * Регистрирует нового пользователя на основе данных из запроса.
     * Создает нового пользователя, сохраняет его в репозитории и генерирует JWT токен.
     *
     * @param request объект запроса с данными нового пользователя
     */
    public void register(RegisterRequest request) {
        // Создание SecurityUser на основе данных из запроса
        SecurityUser securityUser = SecurityUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getEmail())
                .surname(request.getSurname())
                .patronymic(request.getPatronymic())
                .phone(request.getPhone())
                .isActive(true) // todo активность сейчас не учитывается
                .roles(Set.of(UserRole.USER))
                .build();

        SecurityUser savedUser = securityUserService.saveUser(securityUser);
        CalculationEntity currentCalculation = CalculationEntity.builder()
                .isCurrent(true)
                .user(savedUser)
                .build();
        calculationRepository.save(currentCalculation);
    }

    /**
     * Аутентификация пользователя на основе переданного запроса.
     * При успешной аутентификации генерируется JWT токен и возвращается объект ответа с токенами доступа и обновления.
     *
     * @param request объект запроса с данными для аутентификации
     * @return объект ответа со сгенерированными JWT токенами доступа и обновления
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        /*Аутентификация пользователя на основе email и пароля из запроса.
         * authenticationManager выполняет всю работу для аутентификации пользователя.
         * В случае, если почта пользователя или пароль неверны, будет выброшено исключение.*/
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Поиск пользователя по email в репозитории
        SecurityUser securityUser = securityUserService.getUserByEmail(request.getEmail());

        // Генерация JWT токена для пользователя
        String jwtToken = jwtService.generateToken(securityUser);

        // Возвращение объекта ответа со сгенерированным JWT токеном
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void updateCookie(AuthenticationResponse authenticationResponse,
                             HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("token", authenticationResponse.getToken());
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }
}
