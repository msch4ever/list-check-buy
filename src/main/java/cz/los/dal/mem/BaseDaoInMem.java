package cz.los.dal.mem;

import cz.los.dal.BaseDao;
import cz.los.dal.exception.EntityAlreadyExistsException;
import cz.los.model.Entity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public abstract class BaseDaoInMem<T extends Entity> implements BaseDao<T> {

    protected abstract Map<Long, T> readLookup();
    protected abstract void writeLookup(long id, T entity);
    protected abstract T removeLookup(long id);

    protected abstract String getClassName();

    @Override
    public Optional<T> getById(long id) {
        T entity = readLookup().get(id);
        if (entity == null) {
            log.warn("Could not find {} with id:{}", getClassName(), id);
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public boolean save(T entity) {
        if (readLookup().get(entity.getId()) != null) {
            log.error("{}:{} already exists", getClassName(), entity);
            throw new EntityAlreadyExistsException();
        }
        writeLookup(entity.getId(), entity);
        log.info("User:{} has been saved.", entity);
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        if (readLookup().get(id) == null) {
            log.error("{} with id:{} does not exists", getClassName(), id);
            throw new EntityAlreadyExistsException();
        }
        T deleted = removeLookup(id);
        log.info("{}:{} has been deleted.", getClassName(), deleted);
        return true;
    }

    @Override
    public boolean update(T entity) {
        long id = entity.getId();
        if (readLookup().get(id) == null) {
            log.error("{} with id:{} does not exists",getClassName(), id);
            throw new EntityAlreadyExistsException();
        }
        writeLookup(id, entity);
        log.info("{}:{} has been updated.", getClassName(), entity);
        return true;
    }

    @Override
    public List<T> getAll() {
        return List.copyOf(readLookup().values());
    }

}
