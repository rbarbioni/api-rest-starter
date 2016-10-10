package br.com.demo.service;

import br.com.demo.exception.DatabaseException;
import br.com.demo.model.DomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static br.com.demo.Constants.NOT_FOUND_404;


public abstract class BaseService<T extends DomainEntity> {

    private final JpaRepository<T, Long> jpaRepository;
    private final EntityManager entityManager;
    private final Class<T> clazz;

    public BaseService(JpaRepository<T, Long> jpaRepository, EntityManager entityManager, Class<T> clazz) {
        this.jpaRepository = jpaRepository;
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    public T save(T object){
        return this.jpaRepository.save(object);
    }

    public T update(T object){
        this.findOneThrowException(object.getId());
        return this.save(object);
    }

    public T findOneThrowException(Long id){

        T one = this.jpaRepository.findOne(id);
        if(one == null){
            throw new DatabaseException(404, NOT_FOUND_404);
        }
        return one;
    }

    public void delete(Long id){
        this.jpaRepository.delete(this.findOneThrowException(id));
    }

    public void delete(T object){
        this.findOneThrowException(object.getId());
        this.jpaRepository.delete(object);
    }

    public List<T> findAll(){
        return this.jpaRepository.findAll();
    }

    public Page<T> findAll(Pageable pageable){
        return this.jpaRepository.findAll(pageable);
    }

    public T findByOne(T object){
        return this.jpaRepository.getOne(object.getId());
    }

    public T findByOne(Long id){
        return this.jpaRepository.getOne(id);
    }

    public Page<T> search(HttpServletRequest request, Pageable pageable){
        return null;
    }

    public List<T> search(HttpServletRequest request){

        final Map<String, String[]> parameters = request.getParameterMap();

        if(parameters == null && parameters.isEmpty()){
            return null;
        }

        try{

            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(this.clazz);
            Root<T> root = query.from(this.clazz);

            Iterator<Map.Entry<String, String[]>> iterator = parameters.entrySet().iterator();

            while (iterator.hasNext()){

                Map.Entry<String, String[]> next = iterator.next();
                String key = next.getKey();
                String[] value = next.getValue();
                query.where(builder.equal(root.get(key), value[0]));
            }

            return this.entityManager.createQuery(query.select(root)).getResultList();

        }catch (NoResultException e){
            return null;
        }
    }
}
