package order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import order.model.OrderEvent;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
