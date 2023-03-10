package cz.los.dal.mem;

import cz.los.dal.BucketDao;
import cz.los.model.Bucket;
import cz.los.persistance.DB;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class BucketDaoInMem extends BaseDaoInMem<Bucket> implements BucketDao {

    private final DB db;

    public BucketDaoInMem(DB db) {
        this.db = db;
    }

    @Override
    public List<Bucket> getAllByUserId(long userId) {
        return getAll().stream()
                .filter(it -> it.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Bucket> readLookup() {
        return db.getBuckets();
    }

    @Override
    protected void writeLookup(long id, Bucket entity) {
        db.putBucket(id, entity);
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
