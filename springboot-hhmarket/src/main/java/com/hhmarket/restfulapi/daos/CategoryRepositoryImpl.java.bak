package com.hhmarket.restfulapi.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hhmarket.restfulapi.model.Category;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
    public List<Category> findCategoryByCriteria(Set<String> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = cb.createQuery(Category.class);
        Root<Category> rootEntry = query.from(Category.class);

        List<Predicate> predicates = new ArrayList<>();
        
        Path<String> namePath = rootEntry.get("name");
        for (String criterion : criteria) {
            predicates.add(cb.like(namePath, criterion));
        }
        
        Path<String> descriptionPath = rootEntry.get("description");
        for (String criterion : criteria) {
            predicates.add(cb.like(descriptionPath, criterion));
        }
        
        query.select(rootEntry)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
            .getResultList();
    }

	public List<Category> findAll() {
		List<Category> results = entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
		return results;
	}

	public Category findById(Integer id) {
		Category result = entityManager.find(Category.class, id); 
		return result;
	}
	
	public List<Category> findByName(String name) {
		TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
		query.setParameter("name", name);
		List<Category> results = query.getResultList();
		
		return results;
	}
	
	public void save(Category entity) {
		entityManager.persist(entity);
	}
}
