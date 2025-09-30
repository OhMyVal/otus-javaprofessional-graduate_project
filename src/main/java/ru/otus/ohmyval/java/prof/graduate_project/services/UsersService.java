package ru.otus.ohmyval.java.prof.graduate_project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.ohmyval.java.prof.graduate_project.dtos.UserDto;
import ru.otus.ohmyval.java.prof.graduate_project.entities.Book;
import ru.otus.ohmyval.java.prof.graduate_project.entities.User;
import ru.otus.ohmyval.java.prof.graduate_project.repositories.BooksRepository;
import ru.otus.ohmyval.java.prof.graduate_project.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    private final BooksRepository booksRepository;

    @Transactional
    public User createNewUser(UserDto userDto) {
        User user = new User(null, userDto.name(), userDto.eMail());
        return usersRepository.save(user);
    }

    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        usersRepository.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public void removeUserById(Long id) {
        usersRepository.deleteById(id);
    }

    public List<Book> getMyBooks(Long id) {
        Optional <User> user = usersRepository.findById(id);
        return user.get().getBooks();
    }

    public void readBook(Long userId, Long bookId) {
        Optional <User> user = usersRepository.findById(userId);
        Optional <Book> book = booksRepository.findById(bookId);
        user.get().getBooks().add(book.get());
        usersRepository.save(user.get());
    }
}
