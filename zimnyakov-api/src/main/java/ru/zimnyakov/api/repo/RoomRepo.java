package ru.zimnyakov.api.repo;

import org.springframework.data.repository.CrudRepository;
import ru.zimnyakov.api.entity.Room;

import java.util.List;

public interface RoomRepo extends CrudRepository<Room, Long> {

    List<Room> findAll();

}
