package cz.los.persistance;

import cz.los.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DB {

    public static final String DB = "DB";

    private final Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    public synchronized Map<Long, User> getUsers() {
        return users.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().copy()
                ));
    }

    public void putUser(long id, User user) {
        users.put(id, user);
    }

    public User removeUser(long id) {
        return users.remove(id);
    }
}
