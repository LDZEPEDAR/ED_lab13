package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.Producto;
import ed.lab.ed1labo04.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(Producto nuevoProducto) {
        if (nuevoProducto.getPrecio() == null || nuevoProducto.getPrecio() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio debe ser mayor a cero");
        }

        nuevoProducto.setCantidad(0);
        return productoRepository.save(nuevoProducto);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        // Regla: Si no existe, retorna 404.
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public Producto actualizarProducto(Long id, Producto datosActualizados) {
        Producto productoExistente = obtenerProductoPorId(id);

        if (datosActualizados.getPrecio() == null || datosActualizados.getPrecio() <= 0 ||
                datosActualizados.getCantidad() == null || datosActualizados.getCantidad() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Precio o cantidad inválidos");
        }

        productoExistente.setPrecio(datosActualizados.getPrecio());
        productoExistente.setCantidad(datosActualizados.getCantidad());

        return productoRepository.save(productoExistente);
    }
}