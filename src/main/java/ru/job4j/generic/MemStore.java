package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (storage.get(model.getId()) == null) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        return model.equals(storage.put(id, model));
    }

    @Override
    public boolean delete(String id) {
        return storage.remove(id) == null;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
