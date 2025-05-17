package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class LineaPedido {
    private final Producto producto;
    private final int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double subTotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return String.format("%s x %d = %.2f", producto.getNombre(), cantidad, subTotal());
    }
}
// LineaPedido (TERMINADO)
// sacar en lineas el precio y cantidad - Listo

