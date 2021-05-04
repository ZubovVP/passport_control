package ru.job4j.pasport_control.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.pasport_control.models.Passport;
import ru.job4j.pasport_control.repository.PassportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.05.2021.
 */
@Service
@Slf4j
public class PassportService implements Actions<Passport> {
    @Autowired
    private PassportRepository pr;

    @Override
    public Passport save(Passport passport) {
        Passport result = null;
        if (checkParameters(passport)) {
            if (passport.getId() == 0) {
                result = create(passport);
                log.info("Passport created : {} ", passport);
            } else {
                result = update(passport);
                log.info("Passport updated : {} ", passport);
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        if (id > 0) {
            this.pr.deleteById(id);
        }
        return true;
    }

    @Override
    public Passport findById(int id) {
        Passport result = null;
        if (id > 0) {
            result = this.pr.findById(id).orElse(null);
        }
        return result;
    }

    @Override
    public List<Passport> findAll() {
        List<Passport> list = new ArrayList<>();
        this.pr.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Passport> findBySeria(int seria) {
        List<Passport> list = null;
        if (seria == 0 || (int) (Math.log10(seria) + 1) != 6) {
            log.warn("Not performed findBySeria. Seria is 0 or length of numbers isn,t equals 6");
        } else {
            list = this.pr.findBySeria(seria);
        }
        return list;
    }

    @Override
    public Passport findByNumber(int number) {
        Passport result = null;
        if (number == 0 || (int) (Math.log10(number) + 1) != 6) {
            log.warn("Not performed findByNumber. Number is 0 or length of numbers isn,t equals 6");
        } else {
            result = this.pr.findByNumber(number);
        }
        return result;
    }

    @Override
    public List<Passport> findByUnavaliabe() {
        return this.pr.findByUnavaliabe(LocalDate.now());
    }

    @Override
    public List<Passport> findByReplaceable() {
        return this.pr.findByUnavaliabe(LocalDate.now().plusMonths(3));
    }

    private Passport create(Passport passport) {
        passport.setCreated(LocalDate.now());
        passport.setDuration(LocalDate.now().plusYears(10));
        Passport result = pr.save(passport);
        return result;
    }

    private Passport update(Passport passport) {
        return pr.save(passport);
    }

    private boolean checkParameters(Passport passport) {
        boolean result = true;
        if (passport.getName().equals("") || passport.getName().isEmpty()) {
            result = false;
            log.warn("Don't fill name for this passport: {} ", passport);
        } else if (passport.getSurname().equals("") || passport.getSurname().isEmpty()) {
            result = false;
            log.warn("Don't fill surname for this passport: {} ", passport);
        } else if (passport.getSeria() == 0 || (int) (Math.log10(passport.getSeria()) + 1) != 6) {
            result = false;
            log.warn("Don't fill seria or length of numbers isn,t equals 6 for this passport: {} ", passport);
        } else if (passport.getNumber() == 0 || (int) (Math.log10(passport.getNumber()) + 1) != 6) {
            result = false;
            log.warn("Don't fill number or length of numbers isn,t equals 6 for this passport: {} ", passport);
        }
        return result;
    }
}
