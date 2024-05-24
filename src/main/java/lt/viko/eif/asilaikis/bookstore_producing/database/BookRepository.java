package lt.viko.eif.asilaikis.bookstore_producing.database;

import lt.viko.eif.asilaikis.bookstore_producing.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {
}
