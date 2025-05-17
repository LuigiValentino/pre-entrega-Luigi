package com.techlab.app;

import java.util.*;
import com.techlab.productos.*;
import com.techlab.pedidos.*;
import com.techlab.excepciones.StockInsuficienteException;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ProductoService prodSvc = new ProductoService();
    private static final PedidoService pedSvc = new PedidoService();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("=== SISTEMA DE GESTION ===");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear un pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir");
        System.out.print("Elija una opcion: ");
    }
    private static int leerOpcion() {
        String line = sc.nextLine().trim();
        try {
            int opt = Integer.parseInt(line);
            if (opt >= 1 && opt <= 7) return opt;
        } catch (NumberFormatException e) {
        }
        System.out.println("Opcion invalida, intente nuevamente.");
        return -1;
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> altaProducto();
            case 2 -> listaProductos();
            case 3 -> actualizarProducto();
            case 4 -> eliminarProducto();
            case 5 -> crearPedido();
            case 6 -> listarPedidos();
            case 7 -> System.out.println("sistema cerrado.");
            default -> {
            }
        }
    }
    private static void altaProducto() {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(leerLineaNoVacia("Precio"));
            System.out.print("Stock: ");
            int stock = Integer.parseInt(leerLineaNoVacia("Stock"));
            prodSvc.agregarProducto(new Producto(nombre, precio, stock));
            System.out.println("Producto agregado exitosamente.");
        } catch (NumberFormatException ex) {
            System.out.println("Error: debe ingresar un numero valido.");
        }
    }
    private static void listaProductos() {
        List<Producto> list = prodSvc.listarProductos();
        if (list.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            list.forEach(System.out::println);
        }
    }
    private static void actualizarProducto() {
        try {
            System.out.print("ID producto a actualizar: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Producto p = prodSvc.buscarPorId(id);
            if (p == null) {
                System.out.println("Producto no encontrado.");
                return;
            }
            System.out.println("Actualizando: " + p);
            System.out.print("Nuevo precio (ENTER para omitir): ");
            String precioStr = sc.nextLine().trim();
            Double nuevoPrecio = precioStr.isEmpty() ? null : Double.parseDouble(precioStr);
            System.out.print("Nuevo stock (ENTER para omitir): ");
            String stockStr = sc.nextLine().trim();
            Integer nuevoStock = stockStr.isEmpty() ? null : Integer.parseInt(stockStr);
            prodSvc.actualizarProducto(p, null, nuevoPrecio, nuevoStock);
            System.out.println("Producto actualizado: " + p);
        } catch (NumberFormatException ex) {
            System.out.println("Error: entrada invalida.");
        }
    }
    private static void eliminarProducto() {
        try {
            System.out.print("ID producto a eliminar: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("¿Confirma eliminacion? (S/N): ");
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("S") && prodSvc.eliminarProducto(id)) {
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Operacion cancelada o producto no encontrado.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error: ID invalido.");
        }
    }
    private static void crearPedido() {
        Map<Integer,Integer> mapa = new HashMap<>();
        try {
            System.out.print("¿Cuantos productos desea agregar al pedido? ");
            int n = Integer.parseInt(sc.nextLine().trim());
            for (int i = 1; i <= n; i++) {
                System.out.print("ID producto " + i + ": ");
                int id = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Cantidad: ");
                int cant = Integer.parseInt(sc.nextLine().trim());
                mapa.put(id, mapa.getOrDefault(id, 0) + cant);
            }
            Pedido pedido = pedSvc.crearPedido(mapa, prodSvc.listarProductos());
            System.out.println("Pedido creado:");
            System.out.println(pedido);
        } catch (StockInsuficienteException | NoSuchElementException ex) {
            System.out.println("Error al crear pedido: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Error: entrada numerica invalida.");
        }
    }
    private static void listarPedidos() {
        List<Pedido> pedidos = pedSvc.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            pedidos.forEach(p -> {
                System.out.println(p);
                System.out.println("--------------------");
            });
        }
    }
    private static String leerLineaNoVacia(String campo) {
        String line;
        do {
            line = sc.nextLine().trim();
            if (line.isEmpty()) System.out.print(campo + " no puede estar vacio. Intente de nuevo: ");
        } while (line.isEmpty());
        return line;
    }
}
// main (TERMINADO)

// menu incializado - Listo
// esquelo del menu - Listo
// opciones del menu - Listo
// opcion de menu y validacion y lectura - Listo
// ejecutar la opcion elegida - Listo
// agregar producto - Listo
// listar productos - Listo
// buscar y actualizar producto - Listo
// eliminar producto - Listo. Averiguar ID??
// crear pedido - Listo
// listar pedidos - Listo
// leer linea no vacia - Listo. Chequear que salio mal

// flujo de la presentacion
//  Selecciona “1” para Agregar Producto. El programa pregunta el nombre, precio, stock. Se crea un objeto Producto y se agrega a la lista.
//  Selecciona “2” para Listar Productos, y el sistema muestra todos los productos con su id, nombre, precio, stock.
//  Selecciona “5” para Crear Pedido. El sistema pregunta cuántos productos va a agregar, pide ID de producto y cantidad. Verifica stock; si no hay suficiente, lanza StockInsuficienteException o un mensaje apropiado. Si se confirma, descuenta stock y crea el pedido en la colección de pedidos.
//  Selecciona “7” para Salir. El programa finaliza.