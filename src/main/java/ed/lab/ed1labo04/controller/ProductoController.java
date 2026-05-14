package ed.lab.ed1labo04.controller;

import ed.lab.ed1labo04.entity.Producto;
import ed.lab.ed1labo04.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Producto buscarPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }
}