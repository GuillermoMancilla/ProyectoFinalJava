package com.retailjavafinal.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@OneToMany(mappedBy="compra_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compra = new ArrayList<>();*/


    private String nombre, apellido, correoElectronico, direccion, telefono, tipo_usuario, userName, contrasenia;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String correoElectronico, String direccion, String telefono, String tipo_usuario
    , String nombreusuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo_usuario = tipo_usuario;
        this.userName = nombreusuario;
        this.contrasenia = contrasenia;
    }

    /*public Usuario(List<Compra> compra, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String tipo_usuario, String userName, String contrasenia) {
        this.compra = compra;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo_usuario = tipo_usuario;
        this.userName = userName;
        this.contrasenia = contrasenia;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /*public List<Compra> getCompra() {
        return compra;
    }

    public void setCompra(List<Compra> compra) {
        this.compra = compra;
    }*/

    /*public void agregarCompra(Compra compraUsusario) {
        compra.add(compraUsusario);
        compraUsusario.setUsuario(this);
    }*/
}

