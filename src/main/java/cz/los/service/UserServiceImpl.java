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
        Optional<User> userOptional = userDao.getUserById(id);
        return userOptional.map(this::toJson);
    }

    @Override
    public boolean createUser(String name) {
        User user = new User(name);
        return userDao.saveUser(user);
    }

    @Override
    public boolean deleteUserById(long id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public boolean updateUser(long id, String newName) {
        Optional<User> userOptional = userDao.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getName().equals(newName)) {
                return false;
            }
            user.setName(newName);
            userDao.updateUser(user);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllUsers() {
        return userDao.getAllUsers().stream()
                .map(this::toJson)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private String toJson(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }
}
