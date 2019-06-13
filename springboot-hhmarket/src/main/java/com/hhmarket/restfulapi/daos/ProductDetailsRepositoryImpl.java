package com.hhmarket.restfulapi.daos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.hhmarket.restfulapi.model.ProductDetails;
import com.hhmarket.restfulapi.pojo.HttpProductPrices;

@Repository
public class ProductDetailsRepositoryImpl implements ProductDetailsRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<ProductDetails> findAll() {
		List<ProductDetails> results = entityManager.createQuery("SELECT pDt FROM ProductDetails pDt", ProductDetails.class).getResultList();
		return results;
	}
	public Optional<ProductDetails> findById(Integer id) {
		ProductDetails result = entityManager.find(ProductDetails.class, id); 
		return Optional.ofNullable(result);
	}
	
	public List<ProductDetails> findByProductId(int id) {
		TypedQuery<ProductDetails> query = entityManager.createQuery("SELECT pDt FROM ProductDetails pDt "
				+ "WHERE pDt.product.productId = :productId", ProductDetails.class);
		query.setParameter("productId", id);
		List<ProductDetails> results = query.getResultList();
		
		return results;
	}
	
	public ProductDetails findByProductIdAndPrice(int productId, float price) {
		ProductDetails result = null;
		
		TypedQuery<ProductDetails> query = entityManager.createQuery("SELECT pDt FROM ProductDetails pDt "
				+ "WHERE pDt.product.productId = :productId and pDt.price = :price", ProductDetails.class);
		query.setParameter("productId", productId);
		query.setParameter("price", price);
		List<ProductDetails> resultList = query.getResultList();
		if (resultList.size() > 0)
			result = resultList.get(0);
	      
		return result;
	}

	public HttpProductPrices getMinMaxPrice(int productId) {
		HttpProductPrices result = null;
		
		TypedQuery<Object[]> query = entityManager.createQuery(
	              "SELECT pDt.product.productId as productId, MIN(pDt.price) as minPrice, MAX(pDt.price) as maxPrice "
	              + "FROM ProductDetails pDt "
	              + "WHERE pDt.product.productId = :id "
	              + "GROUP BY pDt.product.productId", Object[].class);
		query.setParameter("id", productId);
		List<Object[]> resultList = query.getResultList();
		if (resultList.size() > 0) {
			result = new HttpProductPrices();
			
			Object[] row = resultList.get(0);
			result.setProductId((int) row[0]);
			result.setMinPrice((float) row[1]);
			result.setMaxPrice((float) row[2]);
		}
	      
		return result;
	}

	public void save(ProductDetails entity) {
		entityManager.persist(entity);
	}
}
