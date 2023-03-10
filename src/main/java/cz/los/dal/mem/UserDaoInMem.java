package cz.los.dal.mem;

import cz.los.dal.UserDao;
import cz.los.model.User;
import cz.los.persistance.DB;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserDaoInMem extends BaseDaoInMem<User> implements UserDao {

    private final DB db;

    public UserDaoInMem(DB db) {
        this.db = db;
    }

    @Override
    public Map<Long, User> readLookup() {
        return db.getUsers();
    }

    @Override
    protected void writeLookup(long id, User entity) {
        db.putUser(id ,entity);
    }

    @Override
    protected User removeLookup(long id) {
        return db.removeUser(id);
    }

    @Override
    protected String getClassName() {
        return "User";
    }
}
