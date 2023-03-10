package cz.los.persistance;

import cz.los.model.Bucket;
import cz.los.model.Entity;
import cz.los.model.Item;
import cz.los.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DB {

    public static final String DB = "DB";

    private final Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());
    private final Map<Long, Item> items = Collections.synchronizedMap(new HashMap<>());
    private final Map<Long, Bucket> buckets = Collections.synchronizedMap(new HashMap<>());


    public synchronized <T extends Entity> Map<Long, T> getEntities(Map<Long, T> entities) {
        return entities.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().copy()
                ));
    }

    public synchronized Map<Long, User> getUsers() {
        return getEntities(users);
    }
    public void putUser(long id, User user) {
        users.put(id, user);
    }

    public User removeUser(long id) {
        return users.remove(id);
    }

    public synchronized Map<Long, Item> getItems() {
        return getEntities(items);
    }
    public void putItem(long id, Item item) {
        items.put(id, item);
    }

    public Item removeItem(long id) {
        return items.remove(id);
    }

    public synchronized Map<Long, Bucket> getBuckets() {
        return getEntities(buckets);
    }

    public void putBucket(long id, Bucket bucket) {
        buckets.put(id, bucket);
    }

    public Bucket removeBucket(long id) {
        return buckets.remove(id);
    }

}
