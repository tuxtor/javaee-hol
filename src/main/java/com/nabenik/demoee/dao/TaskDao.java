package com.nabenik.demoee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.nabenik.demoee.model.Task;

/**
 * DAO for Task
 */
@Stateless
public class TaskDao {
	@PersistenceContext(unitName = "javaee-hol-persistence-unit")
	private EntityManager em;

	public void create(Task entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Task entity = em.find(Task.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Task findById(Long id) {
		return em.find(Task.class, id);
	}

	public Task update(Task entity) {
		return em.merge(entity);
	}

	public List<Task> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Task> findAllQuery = em.createQuery(
				"SELECT DISTINCT t FROM Task t ORDER BY t.id", Task.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
