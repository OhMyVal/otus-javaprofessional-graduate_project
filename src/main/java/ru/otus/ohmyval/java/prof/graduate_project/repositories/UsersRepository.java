package ru.otus.ohmyval.java.prof.graduate_project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.ohmyval.java.prof.graduate_project.entities.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
}
