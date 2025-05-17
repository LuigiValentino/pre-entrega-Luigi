package com.techlab.pedidos;

import java.util.*;
import com.techlab.productos.Producto;
import com.techlab.excepciones.StockInsuficienteException;

public class PedidoService {
    private final List<Pedido> pedidos = new ArrayList<>();

    public Pedido crearPedido(Map<Integer, Integer> mapaCantidades, List<Producto> productos)
            throws StockInsuficienteException {
        Pedido pedido = new Pedido();
        for (Map.Entry<Integer, Integer> e : mapaCantidades.entrySet()) {
            Producto prod = null;
            for (Producto p : productos) {
                if (p.getId() == e.getKey()) {
                    prod = p;
                    break;
                }
            }
            if (prod == null) {
                throw new NoSuchElementException("Producto no encontrado: " + e.getKey());
            }
            pedido.agregarLinea(prod, e.getValue());
        }
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return Collections.unmodifiableList(pedidos);
    }
}
// productservice (TERMINADO)
// Validar cantidades de productos - Listo
// Validar que el producto no tenga stock 0 - Listo
// Validar que el producto no tenga id 0 - Listo
// Validar que el producto no tenga nombre vacio - Listo
