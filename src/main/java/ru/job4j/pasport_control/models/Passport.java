package ru.job4j.pasport_control.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.05.2021.
 */
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "seria")
    private int seria;
    @Column(name = "number")
    private int number;
    @Column(name = "created")
    private LocalDate created;
    @Column(name = "duration")
    private LocalDate duration;
}
