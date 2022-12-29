package ru.zimnyakov.api.dto;

import lombok.Data;

@Data
public class PersonUpdate {

    private String name;
    private String passportId;
    private Long room_id;

}
