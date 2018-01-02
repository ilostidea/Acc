package com.bit.common.db;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.bit.acc.model.SysUser;
import com.google.common.base.Preconditions;

public abstract class AbstractDao<T extends Serializable> implements
		IOperations<T> {

	private Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;

	protected final void setClazz(final Class<T> clazzToSet) {
		this.clazz = Preconditions.checkNotNull(clazzToSet);
	}

	@Transactional
	public void persist(T transientInstance) {
		Preconditions.checkNotNull(transientInstance);
		entityManager.persist(transientInstance);
	}

	public void remove(T persistentInstance) {
		Preconditions.checkNotNull(persistentInstance);
		entityManager.remove( entityManager.merge(persistentInstance) );
	}
	
	public void remove(long id) {
		entityManager.remove( entityManager.getReference(clazz, id) );
	}
	
	@Transactional
	public T merge(T persistentInstance) {
		Preconditions.checkNotNull(persistentInstance);
		return entityManager.merge(persistentInstance);
	}

	public T findById(Object id) {
		return (T) entityManager.find(clazz, id);
	}

	public List<T> queryAll() {
		/*TypedQuery<T> typedQuery = entityManager.createNamedQuery("selectAll", clazz);
		return typedQuery.getResultList();*/

		String jpql = "select t from " + clazz.getName() + " as t ";
		List<T> resultList = (List<T>) entityManager.createQuery(jpql).getResultList();
		return resultList;
	}
	
	/*public List<T> findByEnterpriseId(Object enterpriseID) {
		String jpql = "select t from " + clazz.getName() + " as t where t.enterprise.id = :enterprise";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("enterprise", enterpriseID);
		List<T> resultList = (List<T>) query.getResultList();
		return resultList;
	}*/
	
	public List<T> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		//Predicate condition = criteriaBuilder.gt(root.get(Employee_.age), 24);
		criteriaQuery.where();
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public List<T> findAll(String jpql) {
		return entityManager.createQuery(jpql).getResultList();
	}
}
