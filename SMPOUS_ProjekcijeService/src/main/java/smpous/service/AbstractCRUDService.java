package smpous.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.repository.PagingAndSortingRepository;



import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;

public abstract class AbstractCRUDService<T, ID extends Serializable> {

	private PagingAndSortingRepository<T, ID> repo;

	public AbstractCRUDService(PagingAndSortingRepository<T, ID> repo) {
		this.repo = repo;
	}

	public Page<T> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public List<T> findByIds(List<ID> ids) {
		List<T> elements = new ArrayList<T>();
		ids.forEach(id -> elements.add(this.findOne(id)));
		return elements;
	}
	
	public T save(T entity) {
		return repo.save(entity);
	}

	public T findOne(ID id) {
		Optional<T> entitytest = repo.findById(id);
		T entity = entitytest.get();
		return entity;
	}

	public T update(ID id, T newEntity) {
		Optional<T> entitytest = repo.findById(id);
		T entity = entitytest.get();
		try {
			//ignore copy of id field
			BeanUtils.copyProperties(newEntity, entity, "id");
		} catch (Exception e) {
			//logger.warn("while copying properties", e);
			//throw Throwables.propagate(e);
			throw e;
		}

		T updated = repo.save(entity);
		return updated;
	}

	public boolean delete(ID id) {
		repo.deleteById(id);
		return true;
	}
	
	public boolean deleteAll() {
		repo.deleteAll();
		return true;
	}
	
	//a koji ce mi address onda
	
}
