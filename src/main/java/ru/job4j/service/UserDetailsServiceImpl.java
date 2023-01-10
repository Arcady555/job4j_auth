package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository persons;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Person> optionalUser = persons.findByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
        return new User(optionalUser.get().getLogin(), optionalUser.get().getPassword(), emptyList());
    }
}