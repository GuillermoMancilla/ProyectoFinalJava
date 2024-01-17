package com.retailjavafinal.controllers;

import com.retailjavafinal.dao.CompraDao;
import com.retailjavafinal.dao.ProductoDao;
import com.retailjavafinal.dao.UsuarioDao;
import com.retailjavafinal.models.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GestionProductos {
    public void creaProducto(){
        Scanner scanner = new Scanner(System.in);
        String nombreProducto,descripcionproducto,categoria;
        long precio;
        int stock;

        System.out.println("Ingrese nombre del producto");
        nombreProducto = scanner.nextLine();

        System.out.println("Ingrese descripcion del producto");
        descripcionproducto = scanner.nextLine();

        System.out.println("Ingrese categoria del producto");
        categoria = scanner.next();

        System.out.println("Ingrese precio del producto");
        precio = scanner.nextLong();

        System.out.println("Ingrese stock del producto");
        stock = scanner.nextInt();

        Producto producto1 = new Producto(nombreProducto,precio,descripcionproducto,stock,categoria);
        ProductoDao productoDao = new ProductoDao();
        productoDao.insert(producto1);
    }

    public void eliminarProducto(){

        Scanner scanner = new Scanner(System.in);
        ProductoDao productoDao = new ProductoDao();
        String proname, respuesta;
        System.out.println("ingrese el nombre del producto a eliminar");
        proname = scanner.next();
        Producto producto = productoDao.findbyName(proname);
        System.out.println("Producto encontrado: " + producto.getNombre() + " de la categoria: " +
                producto.getCategoria() + "\n¿esta seguro de eliminar este producto? SI/NO");
        respuesta = scanner.next();
        if (respuesta.contains("SI")){
            productoDao.delete(producto);
            System.out.println("Producto eliminado");
        }
        else {
            System.out.println("El producto " + producto.getNombre()  + " no fue eliminado");
        }
    }

    public void busquedaPorCategoria(){
        Scanner scanner = new Scanner(System.in);
        ProductoDao productoDao = new ProductoDao();
        //buscamos las categorias existentes:
        List<String> catprod = productoDao.getCategorias();

        for (String categorias: catprod
             ) {
            System.out.println(categorias + "\n");
        }


        System.out.println("ingrese una categoria de producto a buscar:");
        String categoriabusqueda = scanner.next();


        List<Producto> porductoporCategoria = productoDao.findbyCategoria(categoriabusqueda);

        for (Producto productocategoria: porductoporCategoria)
        {
            System.out.println("El producto encontrado es: " + productocategoria.getNombre()
                    + " con descripcion: " + productocategoria.getDescripcion() + "\n");
        }


    }

    public static  void menuPedido(Usuario usuario){
        Scanner scanner = new Scanner(System.in);
        CarritoDeCompra carritoDeCompra = new CarritoDeCompra();
        while (true) {
            System.out.println("\n---- Menú de compras ----\n");
            System.out.println("1. mostrar catalogo");
            System.out.println("2. Agregar producto al carrito de compras");
            System.out.println("3. Solicitar resumen del carrito de compras");
            System.out.println("4. realizar compra");
            System.out.println("5. Volver al menú principal");

            System.out.print("\nSeleccione una opción: \n");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    long seleccionuser;
                    long cantidadSol;

                    //generamos la fecha
                    LocalDate localDate = LocalDate.now();
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    //Aqui solicitaremos que cliente ingrese el codigo de producto visualizado en el catalogo
                    System.out.println("por favor ingrese el codigo del producto a agregar:");

                    seleccionuser = scanner.nextLong();
                    System.out.println("por favor ingrese la cantidad de productos a agregar");

                    cantidadSol = scanner.nextLong();
                    ProductoDao productoDao = new ProductoDao();
                    Producto itempedido = productoDao.findById(seleccionuser);

                    List<PedidoViewmodel> pedidoAux = new ArrayList<>();

                    PedidoViewmodel pedido = new PedidoViewmodel();
                    pedido.idItem = itempedido.getId();
                    pedido.cantidad = cantidadSol;
                    pedido.precio = itempedido.getPrecio();
                    pedido.total = (pedido.precio*cantidadSol);
                    pedido.fechapedido = date;

                    //carritoDeCompra.pedidosya.add(pedido);

                    pedidoAux.add(pedido);
                    carritoDeCompra.pedidosya = pedidoAux;

                    carritoDeCompra.total = carritoDeCompra.total + pedido.total;


                    break;
                case 3:
                    //aqui recorrer carrito de comrpas para mostrar a cliente su carrito
                    break;
                case 4:
                    //aqui llamamos a los daos con la dara recopilada
                    CompraDao compraDao = new CompraDao();
                    //generamos la fecha
                    LocalDate localDate1 = LocalDate.now();
                    Date date1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Compra compra = new Compra(date1, carritoDeCompra.total);
                    UsuarioDao usuarioDao = new UsuarioDao();
                    usuarioDao.addCompraToUsuario(usuario.getId(),compra);

                    for (PedidoViewmodel det: carritoDeCompra.pedidosya
                         ) {
                        DetalleCompra detalle = new DetalleCompra(det.cantidad, det.precio, det.total, det.fechapedido);

                        compraDao.addDetalleCompra(compra.getid(),detalle, det.idItem);
                    }

                    System.out.println("\nFelicidades, la compra se a realizado con exito, el numero de su orden es: "
                    + compra.getid() + " por un total de: " + compra.getTotal() + "\n");

                    break;
                case 5:
                    System.out.println("\nVolviendo al menú principal.\n");
                    return;
                default:
                    System.out.println("\nOpción no válida. Inténtelo de nuevo.\n");
            }
        }

    }

    public static void mostrarCatalogo() {
        List<Producto> catalogo = obtenerCatalogo();

        System.out.println("\nCatalogo de productos:\n");
        for (int i = 0; i < catalogo.size(); i++) {
            Producto producto = catalogo.get(i);
            //Se modifica para mostrar el codigo de producto
            System.out.println((i + 1) + ". Codigo de producto: " + producto.getId()
                    + " Producto: "+ producto.getNombre() + " - $" + producto.getPrecio());
        }
    }

    public static List<Producto> obtenerCatalogo() {
        return cargarProductos();
    }

    public static List<Producto> cargarProductos() {
        ProductoDao productoDao = new ProductoDao();
        return productoDao.findAll();
    }
}
