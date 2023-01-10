package ru.job4j.controller;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;

@Data
@RestController
@RequestMapping("/users")
public class UserController {
    private final PersonRepository persons;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        persons.save(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return persons.findAll();
    }
}
