package cz.los.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.los.dal.UserDao;
import cz.los.model.User;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<String> getUserById(long id) {
        Optional<User> userOptional = userDao.getById(id);
        return userOptional.map(this::toJson);
    }

    @Override
    public User createUser(String name) {
        User user = new User(name);
        userDao.save(user);
        return user;
    }

    @Override
    public boolean deleteUserById(long id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean updateUser(long id, String newName) {
        Optional<User> userOptional = userDao.getById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getName().equals(newName)) {
                return false;
            }
            user.setName(newName);
            userDao.update(user);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllUsers() {
        return userDao.getAll().stream()
                .map(this::toJson)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public String toJson(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }
}
