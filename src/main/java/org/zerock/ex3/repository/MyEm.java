package org.zerock.ex3.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex3.entity.MyData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MyEm {

    @PersistenceContext
    EntityManager em;

    public List<MyData> findAll(){
        String sql = "select m from MyData m";
        TypedQuery<MyData> query = em.createQuery(sql, MyData.class);

        List<MyData> list = query.getResultList();
        return list;
    }

    @Transactional
    public void insert(MyData myData){
        em.persist(myData);
    }

    @Transactional
    public MyData findById(Long id){
        MyData myData = em.find(MyData.class, id);
        return myData;
    }

    @Transactional
    public void update(MyData myData){
        em.merge(myData);
    }

    @Transactional
    public void deleteById(Long id){
        MyData myData = em.find(MyData.class, id); //<< find 했을 때 영속성 부여
        em.remove(myData);
    }
}
