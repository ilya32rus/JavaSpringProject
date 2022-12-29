package ru.zimnyakov.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zimnyakov.api.dto.PersonUpdate;
import ru.zimnyakov.api.entity.Person;
import ru.zimnyakov.api.repo.PersonRepo;
import ru.zimnyakov.api.repo.RoomRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepo personRepo;
    private final RoomRepo roomRepo;


    @Autowired
    public PersonService(PersonRepo personRepo, RoomRepo roomRepo) {
        this.personRepo = personRepo;
        this.roomRepo = roomRepo;
    }


    public Person createPerson(Person person) {
        return personRepo.save(person);
    }

    public Person getPersonById(Long id) throws EntityNotFoundException {
        Optional<Person> optionalPerson = personRepo.findById(id);
        if (optionalPerson.isEmpty()) throw new EntityNotFoundException();
        return optionalPerson.get();
    }

    public List<Person> getPeople() {
        return personRepo.findAll();
    }

    public Person updatePerson(Long id, PersonUpdate person) throws EntityNotFoundException {
        Optional<Person> optionalPerson = personRepo.findById(id);
        if (optionalPerson.isEmpty()) throw new EntityNotFoundException();
        Person dbPerson = optionalPerson.get();

        // updating fields
        if (!person.getName().isEmpty()) dbPerson.setName(person.getName());
        if (person.getRoom_id() != null) dbPerson.setRoom(roomRepo.findById(person.getRoom_id()).get());
        if (!person.getPassportId().isEmpty()) dbPerson.setPassportId(person.getPassportId());

        return personRepo.save(dbPerson);
    }

    public boolean deletePerson(Long id) {
        Optional<Person> optionalPerson = personRepo.findById(id);
        if (optionalPerson.isEmpty()) throw new EntityNotFoundException();
        Person dbPerson = optionalPerson.get();

        personRepo.delete(dbPerson);
        return true;
    }

}
