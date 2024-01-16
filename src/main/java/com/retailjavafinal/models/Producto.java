package com.retailjavafinal.models;

import org.hibernate.boot.archive.scan.spi.Scanner;

import javax.persistence.*;
import java.util.List;


@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private DetalleCompra tienda;

    private String nombre, descripcion, categoria;
    private long precio;

    private int stock;

    // CONSTRUCTOR

    public Producto() {
    }

    public Producto(String nombre, long precio, String descripcion, int stock, String categoria) {

        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Producto(DetalleCompra tienda, String nombre, long precio, String descripcion, int stock, String categoria) {
        this.tienda = tienda;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleCompra getTienda() {
        return tienda;
    }

    public void setTienda(DetalleCompra tienda) {
        this.tienda = tienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /*public static void listarCategoria(Scanner scanner, Producto productoDao) {

        List<Producto> cateroria = productoDao.findAll();
        for (Producto producto : cateroria) {
            if (producto != null) {
                System.out.println(producto.getCategoria());
                System.out.println();

            }
        }
        System.out.println("------------------------------");
    }*/

}
