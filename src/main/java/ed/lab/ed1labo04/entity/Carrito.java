package ed.lab.ed1labo04.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("cartitems")
    private List<ItemCarrito> items = new ArrayList<>();

    @JsonProperty("totalPrice")
    private Double precioTotal = 0.0;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<ItemCarrito> getItems() { return items; }
    public void setItems(List<ItemCarrito> items) { this.items = items; }

    public Double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }
}