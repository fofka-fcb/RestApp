package ru.myPackage.restApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date created;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore()
    private List<Measurements> measurementsList;

    public Sensor(String name, Date created) {
        this.name = name;
        this.created = created;
    }
}
