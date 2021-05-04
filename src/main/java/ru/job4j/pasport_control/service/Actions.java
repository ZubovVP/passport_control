package ru.job4j.pasport_control.service;

import ru.job4j.pasport_control.models.Passport;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.05.2021.
 */
public interface Actions<E> {
    E save(E element);

    boolean delete(int id);

    E findById(int id);

    List<E> findAll();

    List<E> findBySeria(int seria);

    E findByNumber(int number);


    List<E> findByUnavaliabe();

    List<E> findByReplaceable();

}
