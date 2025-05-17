package com.techlab.pedidos;

import java.util.*;
import com.techlab.productos.Producto;
import com.techlab.excepciones.StockInsuficienteException;

public class Pedido {
    private static int contador = 0;
    private final int id;
    private final List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {
        this.id = ++contador;
    }

    public int getId() {
        return id;
    }

    public void agregarLinea(Producto p, int cantidad) throws StockInsuficienteException {
        if (p.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para: " + p.getNombre());
        }
        p.setStock(p.getStock() - cantidad);
        lineas.add(new LineaPedido(p, cantidad));
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido l : lineas) {
            total += l.subTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido #" + id + "\n");
        for (LineaPedido l : lineas) {
            sb.append("  ").append(l).append("\n");
        }
        sb.append(String.format("Total: %.2f", calcularTotal()));
        return sb.toString();
    }
}

// PEDIDOS (TERMINADO)
// Contadores internos como productos y id - Listo
// Validar que el producto no tenga stock 0 - Listo
// listados de pedidos - Listo
// formato de salida - Listo

