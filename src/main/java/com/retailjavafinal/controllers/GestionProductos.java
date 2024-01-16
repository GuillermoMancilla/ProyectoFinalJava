package com.retailjavafinal.controllers;

import com.retailjavafinal.dao.ProductoDao;
import com.retailjavafinal.models.Producto;

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
}
