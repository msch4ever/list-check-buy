package cz.los.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private static volatile long sequence = 0;
    private final long id;

    private String name;

    public User(String name) {
        this.name = name;
        this.id = generateId();
    }

    private User(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    private static synchronized long generateId() {
        sequence++;
        return sequence;
    }

    public User copy() {
        return new User(this);
    }

}
