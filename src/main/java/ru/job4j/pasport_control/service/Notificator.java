package ru.job4j.pasport_control.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.pasport_control.models.Passport;

import java.util.List;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 06.05.2021.
 */
@Service
@Slf4j
public class Notificator {
    @Autowired
    private RestTemplate rest;
    @Autowired
    private KafkaTemplate<Integer, String> template;

    @Scheduled(fixedDelay = 10000)
    public void checkUnavailablePassports() {
        List<Passport> passports = rest.exchange(
                "http://localhost:8080/passport/unavaliabe",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        for (Passport passport : passports) {
     //       template.send("passport_unavaliabe", passport.getId(), String.format("%s %s, your passport seria - %s number - %s is unavailable", passport.getSurname(), passport.getName(), passport.getNumber()));
            log.info("Sending email... {} {}, your passport is unavailable - passport seria - {} number - {}",passport.getSurname(), passport.getName(), passport.getSeria(), passport.getNumber());
        }
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void checkReplacePassports() {
        List<Passport> passports = rest.exchange(
                "http://localhost:8080/passport/find-replaceable",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        for (Passport passport : passports) {
           // template.send("passport_replace", passport.getId(), String.format("%s %s, your passport seria - %s number - %s should replace", passport.getSurname(), passport.getName(), passport.getNumber()));
            log.info("Sending email... {} {}, you should need replace passport near 3 months for passport seria - {} number - {}",passport.getSurname(), passport.getName(), passport.getSeria(), passport.getNumber());
        }
    }

}
