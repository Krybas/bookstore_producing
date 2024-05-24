package lt.viko.eif.asilaikis.bookstore_producing.model;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.util.List;

@XmlRootElement(name = "OrderList")
@XmlSeeAlso({Order.class, Client.class, Book.class})
public class GenericList<T> {
    private List<T> Data;

    public GenericList() {
    }

    @XmlAnyElement(lax = true)
    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> Data) {
        this.Data = Data;
    }


}