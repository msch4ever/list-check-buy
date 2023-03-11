package cz.los.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "items")
public class Bucket implements Entity {

    private final long id;
    private final long userId;
    private String name;
    private LinkedHashSet<Item> items;

    public Bucket(String name, long userId) {
        this.id = generateId();
        this.userId = userId;
        this.name = name;
        this.items = new LinkedHashSet<>();
    }

    private Bucket(Bucket other) {
        this.id = other.id;
        this.userId = other.userId;
        this.name = other.name;
        this.items = new LinkedHashSet<>(other.items);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItems(List<Item> items) {
        this.items.addAll(items);
    }

    @Override
    public Bucket copy() {
        return new Bucket(this);
    }

}
