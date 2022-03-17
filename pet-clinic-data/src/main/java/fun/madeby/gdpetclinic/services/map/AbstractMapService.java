package fun.madeby.gdpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 */

    public abstract class AbstractMapService<T,ID> {
        protected Map<ID, T> map = new HashMap<>();

        Set<T> findAll(){
            return new HashSet<>(map.values());
        };

        T findById(ID id) {
            return map.get(id);
        }

        T save(ID id,T obj){
            map.put(id, obj);
            return obj;
        }

        void deleteById(ID id) {
            map.remove(id);
        }

        void delete(T obj) {
            map.entrySet().removeIf(e -> e.getValue().equals(obj));
        }

    }
