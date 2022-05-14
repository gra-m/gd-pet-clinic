package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.BaseEntity;
import fun.madeby.gdpetclinic.services.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gra_m on 2022 05 14
 */

public abstract class AbstractJpaService<T extends BaseEntity,
		R extends JpaRepository<T, Long>> implements CrudService<T> {

	protected R repository;

	public AbstractJpaService(R repository) {
		this.repository = repository;
	}

	@Override
	public Set<T> findAll() {
		return new HashSet<>(repository.findAll());
	}

	@Override
	public T findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public T save(T object) {
		return repository.save(object);
	}

	@Override
	public void delete(T object) {
		repository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
