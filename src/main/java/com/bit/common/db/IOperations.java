package com.bit.common.db;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {
	
	public void persist(T transientInstance);

	//public void attachDirty(T instance);

	//public void attachClean(T instance);

	public void remove(T persistentInstance);
	
	public void remove(long id);

	public T merge(T persistentInstance);
	
	//public void refresh(T detachedInstance);

	public T findById(Object primaryKey);
	
	//public List<T> findByExample(T instance);
	
	//public List<T> findByEnterpriseId(Object enterpriseID);

	public List<T> findAll();

	public List<T> findAll(String jpql);
	
	public List<T> queryAll();
}
