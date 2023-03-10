package cz.los.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User implements Entity {

    private final long id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = generateId();
    }

    private User(User other) {
        this.id = other.getId();
        this.name = other.getName();
    }

    @Override
    public User copy() {
        return new User(this);
    }

}
