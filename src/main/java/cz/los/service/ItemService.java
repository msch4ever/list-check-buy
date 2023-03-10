package cz.los.service;

import cz.los.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    String ITEM_SERVICE = "ITEM_SERVICE";

    Optional<String> getItemById(long id);

    Item createItem(String name, String note, int qty, long bucketId);

    boolean deleteItemById(long id);

    boolean updateItem(long id, String newName, String note, int qty);

    List<String> getAllItemsByBucketId(long bucketId);
}
