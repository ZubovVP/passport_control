package ru.job4j.pasport_control.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.05.2021.
 */
@Profile("test")
@Configuration
public class PassportServiceForTest {
    @Bean
    @Primary
    public PassportService nameService() {
        return Mockito.mock(PassportService.class);
    }
}
