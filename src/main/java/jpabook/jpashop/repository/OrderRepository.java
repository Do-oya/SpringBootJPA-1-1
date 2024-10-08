package jpabook.jpashop.repository;

import lombok.RequiredArgsConstructor;
import jpabook.jpashop.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        // 기본 JPQL 쿼리를 사용하여 Order 엔티티를 조회
        return em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }
}