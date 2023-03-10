package cz.los.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.los.dal.ItemDao;
import cz.los.model.Item;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public Optional<String> getItemById(long id) {
        Optional<Item> itemOptional = itemDao.getById(id);
        return itemOptional.map(this::toJson);
    }

    @Override
    public Item createItem(String name, String note, int qty, long bucketId) {
        Item item = new Item(name, note, qty, bucketId);
        itemDao.save(item);
        return item;
    }

    @Override
    public boolean deleteItemById(long id) {
        return itemDao.deleteById(id);
    }

    @Override
    public boolean updateItem(long id, String newName, String note, int qty) {
        Optional<Item> itemOptional = itemDao.getById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            if (Objects.equals(item.getName(), newName)
                    && Objects.equals(item.getNote(), note)
                    && Objects.equals(item.getQty(), qty)) {
                return false;
            }
            item.setName(newName);
            item.setQty(qty);
            itemDao.update(item);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllItemsByBucketId(long bucketId) {
        return itemDao.getAllByBucketId(bucketId).stream()
                .map(this::toJson)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private String toJson(Item item) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(item);
    }
}
