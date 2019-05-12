package com.project.dal;

/**
 * 
 * @author:
 *
 * @param <T>  entity class
 * @param <ID> ID data type
 */
public interface Repository<T, ID> {

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param entity
	 */
	void create(T entity);

	/**
	 * Read
	 * 
	 * @param id
	 * @return
	 */
	T read(ID id);

	/**
	 * Update
	 * 
	 * @param entity
	 * @return
	 */
	T update(T entity);

	/**
	 * Delete
	 * 
	 * @param entity
	 */
	void delete(T entity);

	// end
}