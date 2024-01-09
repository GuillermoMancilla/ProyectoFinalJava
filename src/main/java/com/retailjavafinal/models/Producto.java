package com.retailjavafinal.models;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Entity;

import javax.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private DetalleCompra tienda;

    private String nombre, descripcion;
    private long precio;

    // CONSTRUCTOR

    public Producto() {
    }

    public Producto(DetalleCompra tienda, String nombre, long precio, String descripcion) {
        this.tienda = tienda;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
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

    public double getPrecio() {
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

}
