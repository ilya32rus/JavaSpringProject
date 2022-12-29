package ru.zimnyakov.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String passportId;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
