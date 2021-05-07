package ru.job4j.pasport_control.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.pasport_control.models.Passport;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.05.2021.
 */
@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {

    @Query("select u from Passport u where u.seria = :seria")
    List<Passport> findBySeria(@Param("seria") int seria);

    @Query("select u from Passport u where u.number = :number")
    Passport findByNumber(@Param("number") int number);

    @Query("select u from Passport u where u.duration <= :date")
    List<Passport> findByUnavaliabe(@Param("date") LocalDate date);

    @Query("select u from Passport u where u.duration <= :date and  u.duration >= current_date")
    List<Passport> findByReplaceable(@Param("date") LocalDate date);
}
