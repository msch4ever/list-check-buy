package cz.los.dal.mem;

import cz.los.dal.BucketDao;
import cz.los.model.Bucket;
import cz.los.persistance.DB;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class BucketDaoInMem extends BaseDaoInMem<Bucket> implements BucketDao {

    private final DB db;

    public BucketDaoInMem(DB db) {
        this.db = db;
    }

    @Override
    public Map<Long, Bucket> readLookup() {
        return db.getBuckets();
    }

    @Override
    protected void writeLookup(long id, Bucket entity) {
        db.putBucket(id ,entity);
    }

    @Override
    protected Bucket removeLookup(long id) {
        return db.removeBucket(id);
    }

    @Override
    protected String getClassName() {
        return "Bucket";
    }
}
