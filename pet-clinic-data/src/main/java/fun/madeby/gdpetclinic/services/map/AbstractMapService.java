package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.BaseEntity;
import fun.madeby.gdpetclinic.services.CrudService;

import java.util.*;

/**
 * Created by Gra_m on 2022 03 17
 */

    public abstract class AbstractMapService<T extends BaseEntity> implements CrudService<T> {
        protected Map<Long, T> map = new HashMap<>();

        public Set<T> findAll(){
            return new HashSet<>(map.values());
        };

        public T findById(Long id) {
            return map.get(id);
        }

        public T save(T obj){
            if (obj != null) {
                if (obj.getId() == null) {
                    obj.setId(getNextId());
                }
                map.put(obj.getId(), obj);
            } else {
                throw new RuntimeException("Object cannot be null");
            }
            return obj;
        }

        public void deleteById(Long id) {
            map.remove(id);
        }

        public void delete(T obj) {
            map.entrySet().removeIf(e -> e.getValue().equals(obj));
        }

        private Long getNextId() {
            Long nextId = null;
            try {
                nextId = Collections.max(map.keySet()) + 1;
            } catch (NoSuchElementException e) {
                nextId = 1L;
            }
            return nextId;
        }

    }
