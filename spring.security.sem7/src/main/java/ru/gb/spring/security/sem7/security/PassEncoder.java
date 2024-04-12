package ru.gb.spring.security.sem7.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PassEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        //сдесь должна быть логика шифрования пришедшего от пользователя пароля
        // для того, что бы он соответствовал хранимому на сервере
        return String.valueOf(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //сравнение зашифрованного пароля от юзера, с паролем хранимым в базе
        return encode(rawPassword).equals(encodedPassword);
    }
}
