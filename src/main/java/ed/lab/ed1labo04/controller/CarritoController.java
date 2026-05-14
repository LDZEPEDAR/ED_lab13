package ed.lab.ed1labo04.controller;

import ed.lab.ed1labo04.entity.Carrito;
import ed.lab.ed1labo04.model.PeticionCarrito;
import ed.lab.ed1labo04.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crear(@RequestBody PeticionCarrito peticion) {
        return carritoService.crearCarrito(peticion);
    }

    @GetMapping("/{id}")
    public Carrito buscarPorId(@PathVariable Long id) {
        return carritoService.obtenerCarritoPorId(id);
    }
}