package com.example.example.service;

import com.example.example.dao.PersonDao;
import com.example.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao){
        this.personDao=personDao;

    }

    public int addPerson(Person person){

            return personDao.insertPerson(person);

    }

    public List<Person> getPeople(){

        return personDao.selectedPeople();
    }

    public Optional<Person> getPersonById(UUID id){

        return personDao.selectPersonById(id);
    }

    public void deletePerson(UUID id){

         personDao.deletePersonById(id);
    }

    public void updatePerson(UUID id,Person person){

        personDao.updatePersonById(id, person);
    }
}
