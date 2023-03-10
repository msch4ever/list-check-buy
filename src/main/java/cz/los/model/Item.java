package cz.los.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item implements Entity {

    private final long id;
    private final long bucketId;
    private String name;

    private String note;
    private int qty;
    private boolean checked;

    public Item(String name, String note, int qty, long bucketId) {
        this.id = generateId();
        this.bucketId = bucketId;
        this.name = name;
        this.note = note;
        this.qty = qty;
        this.checked = false;
    }

    private Item(Item other) {
        this.id = other.id;
        this.bucketId = other.bucketId;
        this.name = other.name;
        this.note = other.note;
        this.qty = other.qty;
        this.checked = other.checked;
    }

    @Override
    public Item copy() {
        return new Item(this);
    }

}
