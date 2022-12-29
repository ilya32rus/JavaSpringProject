package ru.zimnyakov.api.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.zimnyakov.api.dto.PersonUpdate;
import ru.zimnyakov.api.entity.Person;
import ru.zimnyakov.api.service.PersonService;

import java.util.List;

@Controller
@RequestMapping("person")
@CrossOrigin
public class PersonController {

    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllElement() {
        try {
            return ResponseEntity.ok(personService.getPeople());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getElement(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personService.getPersonById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> createElement(@RequestBody Person person) {
        try {
            return ResponseEntity.ok(personService.createPerson(person));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateElement(@PathVariable Long id, @RequestBody PersonUpdate person) {
        try {
            return ResponseEntity.ok(personService.updatePerson(id, person));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElement(@PathVariable Long id) {
        try {
            personService.deletePerson(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
