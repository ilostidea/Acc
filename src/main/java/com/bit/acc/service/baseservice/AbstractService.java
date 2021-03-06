package com.bit.acc.service.baseservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public abstract class AbstractService<T extends Serializable, ID extends Serializable> implements Service<T, ID>{

	protected abstract JpaRepository<T, ID> getDao();

	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 * 
	 * @param entity must not be {@literal null}.
	 * @return the saved entity will never be {@literal null}.
	 */
	public <S extends T> S save(S entity) {
		return getDao().save(entity);
	}

	/**
	 * Saves an entity and flushes changes instantly.
	 *
	 * @param entity
	 * @return the saved entity
	 */
	public T saveAndFlush(T entity) {
		return getDao().saveAndFlush(entity);
	}

	/**
	 * Saves all given entities.
	 * 
	 * @param entities must not be {@literal null}.
	 * @return the saved entities will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		return getDao().saveAll(entities);
	}

	/**
	 * Returns a reference to the entity with the given identifier.
	 *
	 * @param id must not be {@literal null}.
	 * @return a reference to the entity with the given identifier.
	 * @throws javax.persistence.EntityNotFoundException if no entity exists for given {@code id}.
	 */
	public T getOne(ID id) {
		return getDao().getOne(id);
	}

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or null if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	public T findById(ID id) {
		return getDao().findById(id).orElse(null);
	}

	/**
	 * Returns a single entity matching the given {@link Example} or {@literal null} if none was found.
	 *
	 * @param example must not be {@literal null}.
	 * @return a single entity matching the given {@link Example} or nll if none was found.
	 * @throws org.springframework.dao.IncorrectResultSizeDataAccessException if the Example yields more than one result.
	 */
	public <S extends T> S findOne(S example) {
		return getDao().findOne( Example.of(example) ).orElse(null);
	}

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> findAllById(Iterable<ID> ids) {
		return getDao().findAllById(ids);
	}

	public List<T> findAll() {
		return getDao().findAll();
	}

	/**
	 * Returns all entities sorted by the given options.
	 * 
	 * @param orderField
	 * @return all entities sorted by the given options
	 */
	public List<T> findAll(Map<String, String> fieldDirections) {
		List<Sort.Order> orders = new ArrayList<>();
		for( Map.Entry<String, String> fieldDirection :  (Set<Map.Entry<String, String>>) fieldDirections.entrySet()  ) {
			String field = fieldDirection.getKey();
			String direction = fieldDirection.getValue();
			orders.add(new Sort.Order(Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC) ,field));
		}
		return getDao().findAll( Sort.by(orders) );
	}

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of entities
	 */
	public Page<T> findAll( Pageable pageable ) {
		return getDao().findAll(pageable);
	}

	/**
	 * Returns all entities matching the given {@link Example}. In case no match could be found an empty {@link Iterable}
	 * is returned.
	 *
	 * @param example must not be {@literal null}.
	 * @return all entities matching the given {@link Example}.
	 */
	public <S extends T> List<S> findAll(S example) {
		return getDao().findAll( Example.of(example) );
	}
	

	/**
	 * Returns all entities matching the given {@link Example} applying the given {@link Sort}. In case no match could be
	 * found an empty {@link Iterable} is returned.
	 *
	 * @param example must not be {@literal null}.
	 * @param orderFields the {@link Sort} specification to sort the results by, must not be {@literal null}.
	 * @return all entities matching the given {@link Example}.
	 * @since 1.10
	 */
	public <S extends T> List<S> findAll(S example, Map<String, String> fieldDirections) {
		List<Sort.Order> orders = new ArrayList<>();
		for( Map.Entry<String, String> fieldDirection :  (Set<Map.Entry<String, String>>) fieldDirections.entrySet()  ) {
			String field = fieldDirection.getKey();
			String direction = fieldDirection.getValue();
			orders.add(new Sort.Order(Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC) ,field));
		}
		return getDao().findAll( Example.of(example), Sort.by(orders));
	}

	/**
	 * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
	 * {@link Page} is returned.
	 *
	 * @param example must not be {@literal null}.
	 * @param pageable can be {@literal null}.
	 * @return a {@link Page} of entities matching the given {@link Example}.
	 */
	public <S extends T> Page<S> findAll(S example, Pageable pageable ) {
		return getDao().findAll( Example.of(example), pageable);
	}

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
	 */
	public void deleteById(ID id) {
		getDao().deleteById(id);
	}

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	public void delete(T entity) {
		getDao().delete(entity);
	}

	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
	 */
	public void deleteAll(Iterable<? extends T> entities) {
		getDao().deleteAll(entities);
	}

	/**
	 * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
	 * the {@link javax.persistence.EntityManager} after the call.
	 *
	 * @param entities
	 */
	public void deleteInBatch(Iterable<T> entities) {
		getDao().deleteInBatch(entities);
	}

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	public boolean existsById(ID id) {
		return getDao().existsById(id);
	}

	/**
	 * Checks whether the data store contains elements that match the given {@link Example}.
	 *
	 * @param example the {@link Example} to use for the existence check. Must not be {@literal null}.
	 * @return {@literal true} if the data store contains elements that match the given {@link Example}.
	 */
	public <S extends T> boolean exists(S example) {
		return getDao().exists( Example.of(example) );
	}

	/**
	 * Returns the number of instances matching the given {@link Example}.
	 *
	 * @param example the {@link Example} to count instances for. Must not be {@literal null}.
	 * @return the number of instances matching the {@link Example}.
	 */
	public <S extends T> long count(S example)  {
		return getDao().count( Example.of(example) );
	}

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	public long count() {
		return getDao().count();
	}
}
