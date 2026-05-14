package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.Carrito;
import ed.lab.ed1labo04.entity.ItemCarrito;
import ed.lab.ed1labo04.entity.Producto;
import ed.lab.ed1labo04.model.PeticionCarrito;
import ed.lab.ed1labo04.repository.CarritoRepository;
import ed.lab.ed1labo04.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Carrito crearCarrito(PeticionCarrito peticion) {
        Carrito nuevoCarrito = new Carrito();
        double precioTotal = 0.0;

        for (PeticionCarrito.ItemPeticion item : peticion.getItems()) {

            if (item.getCantidad() == null || item.getCantidad() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a cero");
            }

            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto no existe"));

            if (producto.getCantidad() < item.getCantidad()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventario insuficiente para: " + producto.getNombre());
            }

            producto.setCantidad(producto.getCantidad() - item.getCantidad());
            productoRepository.save(producto);

            ItemCarrito itemCarrito = new ItemCarrito();
            itemCarrito.setProductoId(producto.getId());
            itemCarrito.setNombre(producto.getNombre());
            itemCarrito.setPrecio(producto.getPrecio());
            itemCarrito.setCantidad(item.getCantidad());

            nuevoCarrito.getItems().add(itemCarrito);

            precioTotal += (producto.getPrecio() * item.getCantidad());
        }

        nuevoCarrito.setPrecioTotal(precioTotal);

        return carritoRepository.save(nuevoCarrito);
    }

    public Carrito obtenerCarritoPorId(Long id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado"));
    }
}