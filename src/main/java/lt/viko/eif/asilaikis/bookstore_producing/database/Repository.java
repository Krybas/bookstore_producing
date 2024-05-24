package lt.viko.eif.asilaikis.bookstore_producing.database;

import lt.viko.eif.asilaikis.bookstore_producing.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Order, Long> {
}
