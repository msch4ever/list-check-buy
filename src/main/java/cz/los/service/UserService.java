package cz.los.service;

import cz.los.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String USER_SERVICE = "USER_SERVICE";

    Optional<String> getUserById(long id);

    String createUser(String name);

    boolean deleteUserById(long id);

    boolean updateUser(long id, String newName);

    List<String> getAllUsers();

}
