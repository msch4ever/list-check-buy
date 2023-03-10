package cz.los.dal.mem;

import cz.los.dal.ItemDao;
import cz.los.model.Item;
import cz.los.persistance.DB;

import java.util.Map;

public class ItemDaoMem extends BaseDaoInMem<Item> implements ItemDao {

    private final DB db;

    public ItemDaoMem(DB db) {
        this.db = db;
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