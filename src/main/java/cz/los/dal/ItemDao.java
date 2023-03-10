package cz.los.dal;

import cz.los.model.Item;

import java.util.List;

public interface ItemDao extends BaseDao<Item> {

    String ITEM_DAO = "ITEM_DAO";

    List<Item> getAllByBucketId(long bucketId);

}
