package com.example.example.api;

import com.example.example.model.Person;
import com.example.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){

        this.personService=personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){

         personService.addPerson(person);

    }

    @GetMapping

    public List<Person> getAllPeople(){

        return personService.getPeople();
    }

    @GetMapping(path="{id}")
    public Person getPersonById(@PathVariable("id") UUID id){

        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path ="{id}")
    public void deletePersonById(@PathVariable("id") UUID id){

             personService.deletePerson(id);

    }

    @PutMapping(path ="{id}")

    public void updatePersonById(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Person personUpdate){

        personService.updatePerson(id, personUpdate);
    }
}
