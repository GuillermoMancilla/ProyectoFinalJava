package com.retailjavafinal.controllers;

import com.retailjavafinal.dao.CompraDao;
import com.retailjavafinal.dao.ProductoDao;
import com.retailjavafinal.dao.UsuarioDao;
import com.retailjavafinal.models.*;

import java.time.LocalDate;
import java.time.ZoneId;
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
            System.out.println("---- Menú de compras ----");
            System.out.println("1. mostrar catalogo");
            System.out.println("2. Agregar producto al carrito de compras");
            System.out.println("3. Solicitar resumen del carrito de compras");
            System.out.println("4. realizar compra");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    //Aqui solicitaremos que cliente ingrese el codigo de producto visualizado en el catalogo
                    System.out.println("por favor ingrese el codigo del producto a agregar:");
                    break;
                case 3:
                    //aqui recorrer carrito de comrpas para mostrar a cliente su carrito
                    break;
                case 4:
                    //aqui llamamos a los daos con la dara recopilada
                    CompraDao compraDao = new CompraDao();
                    //generamos la fecha
                    LocalDate localDate = LocalDate.now();
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Compra compra = new Compra(date, carritoDeCompra.total);
                    UsuarioDao usuarioDao = new UsuarioDao();
                    usuarioDao.addCompraToUsuario(usuario.getId(),compra);

                    for (DetalleCompra det: carritoDeCompra.compras
                         ) {
                        //compraDao.addDetalleCompra(compra1.getid(),det, .getId());
                    }


                    //SECCION COMENTADA PERO GUARDADA TEMPORALMENTE
                    /*//Buscamos la ultima compra realiszada
                    CompraDao ultCpa = new CompraDao();
                    Compra uCompra = ultCpa.getultimaCompra(usuario.getId());*/
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }

    }

    public static void mostrarCatalogo() {
        List<Producto> catalogo = obtenerCatalogo();

        System.out.println("Catalogo de productos:");
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
