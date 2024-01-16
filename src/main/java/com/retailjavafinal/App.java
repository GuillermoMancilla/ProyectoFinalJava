package com.retailjavafinal;

import com.retailjavafinal.controllers.CompraController;
import com.retailjavafinal.controllers.GestionProductos;
import com.retailjavafinal.controllers.GestionUsuario;
import com.retailjavafinal.dao.CompraDao;
import com.retailjavafinal.dao.ProductoDao;
import com.retailjavafinal.dao.UsuarioDao;
import com.retailjavafinal.models.Compra;
import com.retailjavafinal.models.DetalleCompra;
import com.retailjavafinal.models.Producto;
import com.retailjavafinal.models.Usuario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args )
    {
        GestionUsuario GU = new GestionUsuario();
        Usuario userActivo = new Usuario();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---- Menú ----");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear nuevo usuario");
            System.out.println("3. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    userActivo = GU.loginuser();
                    System.out.println("\nUsuario " + userActivo.getUserName() + " inicio sesion.\n");
                    if (userActivo.getTipo_usuario().contains("admin")){
                        menuAdmin();
                    }else{
                        menuUsuarioNormal(scanner,userActivo);
                    }
                    break;
                case 2:
                    //crearUsuario(scanner);
                    GU.creaUsuario();
                    break;
                case 3:
                    System.out.println("Estás saliendo de la tienda,¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }

    }

    private static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        GestionProductos GP = new GestionProductos();
        while (true) {
            System.out.println("---- Menú Administrador ----");
            System.out.println("1. Crear producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Buscar productos por categoria");
            System.out.println("4. Volver al menú principal(cerrar sesion)");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    GP.creaProducto();
                    break;
                case 2:
                    GP.eliminarProducto();
                    break;
                case 3:
                    GP.busquedaPorCategoria();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void menuUsuarioNormal(Scanner scanner, Usuario usuario) {
        //CarritoDeCompras carrito = new CarritoDeCompras();
        CompraController compraController = new CompraController();

        while (true) {
            System.out.println("---- Menú Usuario Normal ----");
            System.out.println("1. Crear pedido");
            System.out.println("2. Ver historial de compras");
            System.out.println("3. Solicitar resumen del carrito de compras");
            System.out.println("4. Elegir despacho a domicilio");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //crearPedido(scanner, carrito, usuario);
                    break;
                case 2:
                    //verHistorialCompras(usuario);
                    compraController.consultaTodasLasComprasDeusuario(usuario);

                    break;
                case 3:
                    //solicitarResumenCarrito(carrito);
                    break;
                case 4:
                    //elegirDespachoDomicilio(scanner, carrito);
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
