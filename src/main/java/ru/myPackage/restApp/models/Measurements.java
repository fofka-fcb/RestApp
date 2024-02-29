package ru.myPackage.restApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "measurements")
@Getter
@Setter
@NoArgsConstructor
public class Measurements {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "temp")
    private Double temp;

    @Column(name = "raining")
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor owner;

    public Measurements(Double temp, Boolean raining) {
        this.temp = temp;
        this.raining = raining;
    }
}
