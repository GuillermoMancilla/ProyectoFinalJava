package com.retailjavafinal.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date fecha;
    private long total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "compra",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> listaDetalleCompra;

    public Compra() {

    }

    public Compra(Date fecha, long total) {
        this.fecha = fecha;
        this.total = total;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCompra> getListaDetalleCompra() {
        return listaDetalleCompra;
    }

    public void setListaDetalleCompra(List<DetalleCompra> listaDetalleCompra) {
        this.listaDetalleCompra = listaDetalleCompra;
    }

    public void agregarCompra(DetalleCompra detalleCompra) {
        listaDetalleCompra.add(detalleCompra);
        detalleCompra.setCompra(this);
    }
}