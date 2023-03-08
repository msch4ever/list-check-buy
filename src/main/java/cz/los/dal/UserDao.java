package cz.los.dal;

import cz.los.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    String USER_DAO = "USER_DAO";

    Optional<User> getUserById(long id);

    boolean saveUser(User user);

    boolean deleteUserById(long id);

    boolean updateUser(User user);

    List<User> getAllUsers();

}
