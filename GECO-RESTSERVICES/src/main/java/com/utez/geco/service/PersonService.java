package com.utez.geco.service;

import com.utez.geco.model.Person;
import com.utez.geco.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository pr;

    public List<Person> findAll() {
        return pr.findAll();
    }

    public Person findById(long id) {
        return pr.findByIdPerson(id);
    }

    public boolean save(Person person) {
        Person saved = pr.save(person);
        return saved != null;
    }

    public long findLastId() {
        return pr.findLastId();
    }

    public boolean update(Person person) {
        Person updated = pr.save(person);
        return updated != null;
    }
}
