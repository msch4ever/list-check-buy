package cz.los.dal;

import cz.los.model.Entity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends Entity> {

    Optional<T> getById(long id);

    boolean save(T user);

    boolean deleteById(long id);

    boolean update(T user);

    List<T> getAll();

}
