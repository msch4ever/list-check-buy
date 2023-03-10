package cz.los.model;

public interface Entity {

    long getId();

    <T extends Entity> T copy();

    default long generateId() {
        return IdGenerator.getGenerator().generateId();
    }

}
