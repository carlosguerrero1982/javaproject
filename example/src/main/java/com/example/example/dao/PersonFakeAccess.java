package com.example.example.dao;

import com.example.example.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class PersonFakeAccess implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectedPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {

        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;

    }

    @Override
    public void updatePersonById(UUID id, Person update) {
        selectPersonById(id)

                .map(person -> {

                    int indexPersonDelete = DB.indexOf(person);

                    if (indexPersonDelete >= 0) {

                        DB.set(indexPersonDelete, new Person(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }




    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }


}

