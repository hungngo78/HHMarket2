package com.hhmarket.restfulapi.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

import com.hhmarket.restfulapi.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
    public List<Product> findProductByCriteria(int categoryId, Set<String> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> rootEntry = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        
        // search by product name and description
        Path<String> namePath1 = rootEntry.get("name");
        for (String criterion : criteria) {
            predicates.add(cb.like(namePath1, "%" + criterion + "%"));
        }
        Path<String> descriptionPath1 = rootEntry.get("description");
        for (String criterion : criteria) {
            predicates.add(cb.like(descriptionPath1, "%" + criterion + "%"));
        }
        
        // search by category name and description
        Path<String> namePath2 = rootEntry.get("category").get("name");
        for (String criterion : criteria) {
            predicates.add(cb.like(namePath2, "%" + criterion + "%"));
        }
        Path<String> descriptionPath2 = rootEntry.get("category").get("description");
        for (String criterion : criteria) {
            predicates.add(cb.like(descriptionPath2, "%" + criterion + "%"));
        }
        
        if (categoryId > 0) {
        	Path<Integer> categoryIdPath = rootEntry.get("category").get("categoryId");
        	Predicate categoryIdPredicate = cb.equal(categoryIdPath, categoryId);
        	
            query.select(rootEntry)
        	.where(categoryIdPredicate, cb.or(predicates.toArray(new Predicate[predicates.size()])));
        } else {
        	query.select(rootEntry)
        	.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        }

        return entityManager.createQuery(query)
            .getResultList();
    }

	public List<Product> findAll() {
		List<Product> results = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
		return results;
	}

	public Optional<Product> findById(int id) {
		Product result = entityManager.find(Product.class, id); 
		return Optional.ofNullable(result);
	}
	
	public List<Product> findByName(String name) {
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
		query.setParameter("name", name);
		List<Product> results = query.getResultList();
		
		return results;
	}
	
	public List<Product> findByCategoryId(int categoryId) {
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId", Product.class);
		query.setParameter("categoryId", categoryId);
		List<Product> results = query.getResultList();
		
		return results;
	}
	
	public void save(Product entity) {
		entityManager.persist(entity);
	}
}
