package ru.otus.ohmyval.java.prof.graduate_project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.ohmyval.java.prof.graduate_project.dtos.BookDto;
import ru.otus.ohmyval.java.prof.graduate_project.entities.Book;
import ru.otus.ohmyval.java.prof.graduate_project.entities.User;
import ru.otus.ohmyval.java.prof.graduate_project.repositories.BooksRepository;
import ru.otus.ohmyval.java.prof.graduate_project.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;

    private final UsersRepository usersRepository;

    public Optional<Book> getBookById(Long id) {
        return booksRepository.findById(id);
    }

    @Transactional
    public Book createNewBook(BookDto bookDto){
        Book book = new Book(null, bookDto.author(), bookDto.title(), bookDto.available());
        return booksRepository.save(book);
    }

    @Transactional
    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        booksRepository.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : booksRepository.findAll()) {
            if (book.getAvailable()) result.add(book);
        }
        return result;
    }

    @Transactional
    public void removeBook(Long id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public List<User> getMyReaders(Long id) {
        Optional <Book> book = booksRepository.findById(id);
        if (book.isPresent()) return book.get().getUsers();
        return null;
    }

    @Transactional
    public void makeAvailable(Long id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            book.get().setAvailable(true);
            booksRepository.save(book.get());
        }
    }
}
