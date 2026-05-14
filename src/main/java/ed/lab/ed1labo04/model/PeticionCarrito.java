package ed.lab.ed1labo04.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PeticionCarrito {

    @JsonProperty("cartitems")
    private List<ItemPeticion> items;

    public List<ItemPeticion> getItems() {
        return items;
    }

    public void setItems(List<ItemPeticion> items) {
        this.items = items;
    }

    public static class ItemPeticion {
        @JsonProperty("productId")
        private Long productoId;

        @JsonProperty("quantity")
        private Integer cantidad;

        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
    }
}