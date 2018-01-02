package com.bit.common.db;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public abstract class AbstractService<T extends Serializable> implements
		IOperations<T> {

	protected abstract IOperations<T> getDao();

	public void persist(T transientInstance) {
		getDao().persist(transientInstance);
	}

	/*public void attachDirty(T instance) {
		getDao().attachDirty(instance);
	}

	public void attachClean(T instance) {
		getDao().attachClean(instance);
	}*/

	public void remove(T persistentInstance) {
		getDao().remove(persistentInstance);
	}
	
	public void remove(long id) {
		getDao().remove(id);
	}
	
	public T merge(T persistentInstance) {
		return getDao().merge(persistentInstance);
	}

	/*public T merge(T detachedInstance) {
		return getDao().merge(detachedInstance);
	}*/

	public T findById(Object id) {
		return getDao().findById(id);
	}

	/*public List<T> findByExample(T instance) {
		return getDao().findByExample(instance);
	}*/
	
	/*public List<T> findByEnterpriseId(Object enterpriseID) {
		return getDao().findByEnterpriseId(enterpriseID);
	}*/

	public List<T> findAll() {
		return getDao().findAll();
	}
	
	public List<T> findAll(String jpql) {
		return getDao().findAll( jpql );
	}
	
	@Override
	public List<T> queryAll() {
		return getDao().queryAll();
	}

}
