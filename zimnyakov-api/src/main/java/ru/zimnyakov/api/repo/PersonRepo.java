package ru.zimnyakov.api.repo;

import org.springframework.data.repository.CrudRepository;
import ru.zimnyakov.api.entity.Person;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person, Long> {

    List<Person> findAll();

}
