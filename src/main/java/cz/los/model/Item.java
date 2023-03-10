package cz.los.model;

import lombok.Getter;

@Getter
public class Item implements Entity {

    private final long id;
    private final long bucketId;
    private String name;
    private int qty;
    private boolean checked;

    public Item(String name, int qty, long bucketId) {
        this.id = generateId();
        this.bucketId = bucketId;
        this.name = name;
        this.qty = qty;
        this.checked = false;
    }

    private Item(Item other) {
        this.id = other.id;
        this.bucketId = other.bucketId;
        this.name = other.name;
        this.qty = other.qty;
        this.checked = other.checked;
    }

    public Item(String name, long bucketId) {
        this(name, 1, bucketId);
    }

    @Override
    public Item copy() {
        return new Item(this);
    }

}
