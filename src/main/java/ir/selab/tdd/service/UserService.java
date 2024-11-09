package ir.selab.tdd.service;

import ir.selab.tdd.domain.User;
import ir.selab.tdd.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public boolean loginWithUsername(String username, String password) {
        User userByUsername = repository.getUserByUsername(username);
        if (userByUsername == null) {
            return false;
        }
        return userByUsername.getPassword().equals(password);
    }

    public boolean loginWithEmail(String username, String password) {
        User userByEmail = repository.getUserByEmail(username);
        if (userByEmail == null) {
            return false;
        }
        return userByEmail.getPassword().equals(password);
    }

    public boolean registerUser(String username, String password) {
        User user = new User(username, password);
        return repository.addUser(user);
    }

    public boolean registerUser(String username, String password, String email) {
        User user = new User(username, password);
        user.setEmail(email);
        return repository.addUser(user);
    }

    public boolean removeUser(String username) {
        User userByUsername = repository.getUserByUsername(username);
        if (userByUsername != null) {
            return repository.removeUser(userByUsername);
        }
        return false;
    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    public boolean changeUserEmail(String username, String newEmail) {
        User userByUsername = repository.getUserByUsername(username);
        if (userByUsername != null) {
            userByUsername.setEmail(newEmail);
            return true;
        }
        return false;
    }
}
