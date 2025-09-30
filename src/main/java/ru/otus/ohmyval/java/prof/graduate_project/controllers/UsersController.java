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
import ru.otus.ohmyval.java.prof.graduate_project.services.UsersService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    private static final Function<User, UserDto> USER_TO_DTO = i -> new UserDto(i.getId(), i.getName(), i.getEMail());
    private static final Function<Book, BookDto> BOOK_TO_DTO = i -> new BookDto(i.getId(), i.getAuthor(), i.getTitle());

    @GetMapping
    public List<UserDto> getAllUsers() {
        logger.info("List of all users");
        return usersService.getAllUsers().stream().map(USER_TO_DTO).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createNewUser(@RequestBody UserDto createUserDto) {
            User newUser = usersService.createNewUser(createUserDto);
            logger.info("New user created");
            return new UserDto(newUser.getId(), newUser.getName(), newUser.getEMail());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUserById(@PathVariable Long id) {
        usersService.removeUserById(id);
        logger.info("User with id = " + id + " deleted");
        return "\"User with id = \" + id + \" deleted\"";
    }
    @GetMapping("/my_books/{id}")
    public List<BookDto> showMyBooks(@PathVariable Long id){
        logger.info("List of my books");
        return usersService.getMyBooks(id).stream().map(BOOK_TO_DTO).collect(Collectors.toList());
    }
    @GetMapping("/my_books/{user_id}/{book_id}")
    @ResponseStatus(HttpStatus.OK)
    public String readBook(
            @PathVariable (name = "user_id") Long userId,
            @PathVariable(name = "book_id") Long bookId
    ) {
        usersService.readBook(userId, bookId);
        logger.info("User id = " + userId + " read book id = " + bookId);
        return "\"User id = \" + userId + \" read book id = \" + bookId";
    }
}
