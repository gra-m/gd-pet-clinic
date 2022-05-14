package fun.madeby.gdpetclinic.services;


import java.util.Set;


/**
 * Created by Gra_m on 2022 03 17
 * Mimicking but not completely copying CRUD interface
 */

public interface CrudService<T> {

    Set<T> findAll();

    T findById(Long id);

    T save(T object);

    void delete(T object);

    void deleteById(Long id);

}
