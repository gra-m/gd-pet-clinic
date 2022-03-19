package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.BaseEntity;

import java.util.*;

/**
 * Created by Gra_m on 2022 03 17
 */

    public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
        protected Map<Long, T> map = new HashMap<>();

        Set<T> findAll(){
            return new HashSet<>(map.values());
        };

        T findById(ID id) {
            return map.get(id);
        }

        T save(T obj){
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

        void deleteById(ID id) {
            map.remove(id);
        }

        void delete(T obj) {
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
