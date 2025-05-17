package com.techlab.productos;

import java.util.*;

public class ProductoService {
    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> listarProductos() {
        return Collections.unmodifiableList(productos);
    }
    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }
    public void actualizarProducto(Producto p, String nombre, Double precio, Integer stock) {
        if (nombre != null && !nombre.isBlank()) {
            p.setNombre(nombre);
        }
        if (precio != null && precio >= 0) {
            p.setPrecio(precio);
        }
        if (stock != null && stock >= 0) {
            p.setStock(stock);
        }
    }
}

// Buscar si el producto existe pero no se puede eliminar, ID no se puede eliminar.

// Validar (TERMINADO):
// validar que el producto no exista - listo
// validar que el producto no tenga stock 0 - listo
// validar que el producto no tenga id 0 - listo
// validar que el producto no tenga nombre vacio - listo
// validar que el producto no tenga precio negativo - listo
// validar que el producto no tenga stock negativo - listo
