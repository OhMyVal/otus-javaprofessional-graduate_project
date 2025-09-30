package ru.otus.ohmyval.java.prof.graduate_project.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.ohmyval.java.prof.graduate_project.dtos.BookDto;
import ru.otus.ohmyval.java.prof.graduate_project.dtos.UserDto;
import ru.otus.ohmyval.java.prof.graduate_project.entities.Book;
import ru.otus.ohmyval.java.prof.graduate_project.entities.User;
import ru.otus.ohmyval.java.prof.graduate_project.services.BooksService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    private static Logger logger = LoggerFactory.getLogger(BooksController.class);

    private static final Function<Book, BookDto> BOOK_TO_DTO = i -> new BookDto(i.getId(), i.getAuthor(), i.getTitle());

    private static final Function<User, UserDto> USER_TO_DTO = i -> new UserDto(i.getId(), i.getName(), i.getEMail());

    @GetMapping
    public List<BookDto> getAllBooks() {
        logger.info("List of all books");
        return booksService.getAllBooks().stream().map(BOOK_TO_DTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
            return booksService.getBookById(id).map(BOOK_TO_DTO).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createNewBook(@RequestBody BookDto createBookDto) {
            Book newBook = booksService.createNewBook(createBookDto);
            return new BookDto(newBook.getId(), newBook.getAuthor(), newBook.getTitle());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteBookById(@PathVariable Long id) {
        booksService.removeBook(id);
        logger.info("Book with id = " + id + " deleted");
        return "\"Book with id = \" + id + \" deleted\"";
    }

    @GetMapping("/my_readers/{id}")
    public List<UserDto> showMyReaders(@PathVariable Long id){
        logger.info("List of my readers");
        return booksService.getMyReaders(id).stream().map(USER_TO_DTO).collect(Collectors.toList());
    }
}
