package fun.madeby.gdpetclinic.services;

import java.util.List;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 * Mimicking but not completely copying CRUD interface
 *
 * Generics used T type and ID
 */

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);



}
