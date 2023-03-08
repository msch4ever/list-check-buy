package cz.los.dal;

import cz.los.dal.exception.UserAlreadyExistsException;
import cz.los.model.User;
import cz.los.persistance.DB;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class UserDaoInMem implements UserDao {

    private final DB db;

    public UserDaoInMem(DB db) {
        this.db = db;
    }

    @Override
    public Optional<User> getUserById(long id) {
        User user = db.getUsers().get(id);
        if (user == null) {
            log.warn("Could not find user with id:{}", id);
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public boolean saveUser(User user) {
        if (db.getUsers().get(user.getId()) != null) {
            log.error("User:{} already exists", user);
            throw new UserAlreadyExistsException();
        }
        db.putUser(user.getId(), user);
        log.info("User:{} has been saved.", user);
        return true;
    }

    @Override
    public boolean deleteUserById(long id) {
        if (db.getUsers().get(id) == null) {
            log.error("User with id:{} does not exists", id);
            throw new UserAlreadyExistsException();
        }
        User deleted = db.removeUser(id);
        log.info("User:{} has been deleted.", deleted);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        long userId = user.getId();
        if (db.getUsers().get(userId) == null) {
            log.error("User with id:{} does not exists", userId);
            throw new UserAlreadyExistsException();
        }
        db.putUser(userId, user);
        log.info("User:{} has been updated.", user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(db.getUsers().values());
    }
}
