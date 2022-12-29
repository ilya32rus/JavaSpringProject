package ru.zimnyakov.api.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.zimnyakov.api.entity.Room;
import ru.zimnyakov.api.service.RoomService;

import java.util.List;

@Controller
@RequestMapping("room")
@CrossOrigin
public class RoomController {

    private final RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllElement() {
        try {
            return ResponseEntity.ok(roomService.getAllRoom());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getElement(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roomService.getRoomById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Room> createElement(@RequestBody Room element) {
        try {
            return ResponseEntity.ok(roomService.createShop(element));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateElement(@PathVariable Long id, @RequestBody Room element) {
        try {
            return ResponseEntity.ok(roomService.updateRoom(id, element));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElement(@PathVariable Long id) {
        try {
            roomService.deleteShop(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
