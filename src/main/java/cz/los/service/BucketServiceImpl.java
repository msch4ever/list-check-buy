package cz.los.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.los.dal.BucketDao;
import cz.los.model.Bucket;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BucketServiceImpl implements BucketService {

    private final BucketDao bucketDao;

    public BucketServiceImpl(BucketDao bucketDao) {
        this.bucketDao = bucketDao;
    }

    @Override
    public Optional<String> getBucketById(long id) {
        Optional<Bucket> BucketOptional = bucketDao.getById(id);
        return BucketOptional.map(this::toJson);
    }

    @Override
    public Bucket createBucket(String name, long userId) {
        Bucket bucket = new Bucket(name, userId);
        bucketDao.save(bucket);
        return bucket;
    }

    @Override
    public boolean deleteBucketById(long id) {
        return bucketDao.deleteById(id);
    }

    @Override
    public boolean updateBucket(long id, String newName) {
        Optional<Bucket> bucketOptional = bucketDao.getById(id);
        if (bucketOptional.isPresent()) {
            Bucket bucket = bucketOptional.get();
            if (bucket.getName().equals(newName)) {
                return false;
            }
            bucket.setName(newName);
            bucketDao.update(bucket);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBucket(Bucket updated) {
        Optional<Bucket> existingBucketOptional = bucketDao.getById(updated.getId());
        if (existingBucketOptional.isPresent()) {
            Bucket existing = existingBucketOptional.get();
            if (Objects.equals(existing.getName(), updated.getName())
                    && Objects.equals(existing.getItems(), updated.getItems())) {
                return false;
            }
            bucketDao.update(updated);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllBucketsByUserId(long userId) {
        return bucketDao.getAllByUserId(userId).stream()
                .map(this::toJson)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public String toJson(Bucket Bucket) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(Bucket);
    }
    
}
