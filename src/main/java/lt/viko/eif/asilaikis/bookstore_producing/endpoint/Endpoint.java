package lt.viko.eif.asilaikis.bookstore_producing.endpoint;

import eif.viko.lt.asilaikis.springsoap.gen.*;
import lt.viko.eif.asilaikis.bookstore_producing.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@org.springframework.ws.server.endpoint.annotation.Endpoint
public class Endpoint {
    private static final String NAMESPACE_URI = "http://lt.viko.eif/asilaikis/springsoap/gen";
    private Service service;

    @Autowired
    public Endpoint(Service repository) {
        this.service = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientOrdersRequest")
    @ResponsePayload
    public GetClientOrdersResponse getOrder(@RequestPayload GetClientOrdersRequest request) {
        GetClientOrdersResponse response = new GetClientOrdersResponse();
        response.setOrderList(service.findOrders(request.getFirstName(),request.getLastName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.setBook(service.findBook(request.getBookName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookOrdersRequest")
    @ResponsePayload
    public GetBookOrdersResponse getBookOrder(@RequestPayload GetBookOrdersRequest request) {
        GetBookOrdersResponse response = new GetBookOrdersResponse();
        response.setBooks(service.findOrderedBook(request.getBook()));
        return response;
    }
}
