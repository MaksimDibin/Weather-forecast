package ru.dibin.persist;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "weather_history")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String value;

    public Weather() {
    }

    public Weather(Date date, String value) {
        this.date = date;
        this.value = value;
    }
}
