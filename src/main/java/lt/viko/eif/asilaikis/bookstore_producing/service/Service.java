package lt.viko.eif.asilaikis.bookstore_producing.service;

import eif.viko.lt.asilaikis.springsoap.gen.BookList;
import eif.viko.lt.asilaikis.springsoap.gen.OrderList;
import lt.viko.eif.asilaikis.bookstore_producing.database.BookRepository;
import lt.viko.eif.asilaikis.bookstore_producing.database.Repository;
import lt.viko.eif.asilaikis.bookstore_producing.model.Book;
import lt.viko.eif.asilaikis.bookstore_producing.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Order> list() {
        return repository.findAll();
    }

    public List<Book> bookList() {
        return bookRepository.findAll();
    }
    private OrderList convertToDto(List<Order> order) {
        OrderList orderList = new OrderList();
        for (Order order1 : order) {
            eif.viko.lt.asilaikis.springsoap.gen.Order orderDto = (modelMapper.map(order1, eif.viko.lt.asilaikis.springsoap.gen.Order.class));
            orderList.getOrder().add(orderDto);
        }
        return orderList;
    }
    private BookList convert(List<Book> book) {
        BookList bookList = new BookList();
        for (Book book1 : book) {
            eif.viko.lt.asilaikis.springsoap.gen.Book bookDto = (modelMapper.map(book1, eif.viko.lt.asilaikis.springsoap.gen.Book.class));
            bookList.getBook().add(bookDto);
        }
        return bookList;
    }

    public OrderList findOrders(String firstName, String lastName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getClient().getFirstName().equals(firstName) &&
                        order.getClient().getLastName().equals(lastName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }

    public BookList findBook (String bookName) {
        List<Book> filteredBook = this.bookList().stream()
                .filter(book -> book.getBookName().equals(bookName))
                .collect(Collectors.toList());
        return convert(filteredBook);
    }

    public OrderList findOrderedBook (String bookName) {
        List<Order> filteredOrders = this.list().stream()
                .filter(order -> order.getBook().getBookName().equals(bookName))
                .collect(Collectors.toList());
        return convertToDto(filteredOrders);
    }
}
