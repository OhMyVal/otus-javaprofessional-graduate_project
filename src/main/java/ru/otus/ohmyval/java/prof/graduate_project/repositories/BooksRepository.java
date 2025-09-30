package ru.otus.ohmyval.java.prof.graduate_project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.ohmyval.java.prof.graduate_project.entities.Book;

@Repository
public interface BooksRepository extends CrudRepository<Book, Long> {
}
