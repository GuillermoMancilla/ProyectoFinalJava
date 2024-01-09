package com.retailjavafinal.models;

import javax.persistence.*;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long cantidad, precio, total;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Compra compra;

//    CONSTRUCTOR


    public DetalleCompra() {
    }

    public DetalleCompra(long cantidad, long precio, long total, Producto producto, Compra compra) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.producto = producto;
        this.compra = compra;
    }

    public long getid() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}