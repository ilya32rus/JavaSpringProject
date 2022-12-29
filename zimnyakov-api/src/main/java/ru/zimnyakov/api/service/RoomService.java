package ru.zimnyakov.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zimnyakov.api.entity.Room;
import ru.zimnyakov.api.repo.RoomRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepo roomRepo;

    @Autowired
    public RoomService(RoomRepo elementRepo) {
        this.roomRepo = elementRepo;
    }

    public Room createShop(Room room) {
        return roomRepo.save(room);
    }

    public Room getRoomById(Long id) throws EntityNotFoundException {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (optionalRoom.isEmpty()) throw new EntityNotFoundException();
        return optionalRoom.get();
    }

    public List<Room> getAllRoom() {
        return roomRepo.findAll();
    }

    public Room updateRoom(Long id, Room element) throws EntityNotFoundException {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (optionalRoom.isEmpty()) throw new EntityNotFoundException();
        Room dbElement = optionalRoom.get();

        // updating fields
        if (!element.getType().isEmpty()) dbElement.setType(element.getType());
        if (element.getLevel() != null) dbElement.setLevel(element.getLevel());
        if (!element.getStyle().isEmpty()) dbElement.setStyle(element.getStyle());

        return roomRepo.save(dbElement);
    }

    public boolean deleteShop(Long id) {
        Optional<Room> optionalElement = roomRepo.findById(id);
        if (optionalElement.isEmpty()) throw new EntityNotFoundException();
        Room dbElement = optionalElement.get();

        roomRepo.delete(dbElement);
        return true;
    }


}
