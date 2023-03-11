package cz.los.service;

import cz.los.model.Bucket;

import java.util.List;
import java.util.Optional;

public interface BucketService {

    String BUCKET_SERVICE = "BUCKET_SERVICE";

    Optional<String> getBucketById(long id);

    Bucket createBucket(String name, long userId);

    boolean deleteBucketById(long id);

    boolean updateBucket(long id, String newName);

    boolean updateBucket(Bucket updated);

    List<String> getAllBucketsByUserId(long userId);

    String toJson(Bucket bucket);

}
