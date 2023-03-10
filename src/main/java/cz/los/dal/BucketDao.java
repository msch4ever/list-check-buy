package cz.los.dal;

import cz.los.model.Bucket;

import java.util.List;

public interface BucketDao extends BaseDao<Bucket> {

    String BUCKET_DAO = "BUCKET_DAO";

    List<Bucket> getAllByUserId(long userId);

}
