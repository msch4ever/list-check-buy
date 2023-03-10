package cz.los.dal.mem;

import cz.los.dal.ItemDao;
import cz.los.model.Item;
import cz.los.persistance.DB;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemDaoMem extends BaseDaoInMem<Item> implements ItemDao {

    private final DB db;

    public ItemDaoMem(DB db) {
        this.db = db;
    }

    @Override
    public List<Item> getAllByBucketId(long bucketId) {
        return getAll().stream()
                .filter(it -> it.getBucketId() == bucketId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Item> readLookup() {
        return db.getItems();
    }

    @Override
    protected void writeLookup(long id, Item entity) {
        db.putItem(id, entity);
    }

    @Override
    protected Item removeLookup(long id) {
        return db.removeItem(id);
    }

    @Override
    protected String getClassName() {
        return "Item";
    }
}