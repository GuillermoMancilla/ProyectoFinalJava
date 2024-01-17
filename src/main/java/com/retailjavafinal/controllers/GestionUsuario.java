package com.retailjavafinal.controllers;

import com.retailjavafinal.dao.UsuarioDao;
import com.retailjavafinal.models.Usuario;

import java.util.Scanner;

public class GestionUsuario {
    public void creaUsuario(){
        Scanner scanner = new Scanner(System.in);

        UsuarioDao usuarioDao = new UsuarioDao();
        String nombre;
        String apellido;
        String direccion;
        String mail;
        String telefono;
        String username;
        String contrasenia;
        String tipoUsuario = "";
        int seleccion = 0;

        System.out.println("Se iniciara el proceso para crear un usuario");

        System.out.println("ingrese nombre:");
        nombre = scanner.nextLine();

        System.out.println("ingrese apellido:");
        apellido = scanner.nextLine();

        System.out.println("ingrese direccion:");
        direccion = scanner.nextLine();

        System.out.println("ingrese mail:");
        mail = scanner.nextLine();

        System.out.println("ingrese telefono:");
        telefono = scanner.nextLine();

        System.out.println("ingrese Nombre de usuario:");
        username = scanner.nextLine();

        System.out.println("ingrese contraseña:");
        contrasenia = scanner.nextLine();

        System.out.println("Indique el tipo de usuario a crear:\n1.-admin\n2.-cliente");
        seleccion = scanner.nextInt();

        switch (seleccion) {
            case 1:
                tipoUsuario = "admin";
                break;
            case 2:
                tipoUsuario = "cliente";
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }

        Usuario user = new Usuario(nombre,apellido,mail,direccion,telefono,tipoUsuario,username,contrasenia);

        usuarioDao.insert(user);
        System.out.println("\n¡Usuario creado exitosamente!\n");
    }

    public Usuario loginuser(){
        String userName, pass;
        Usuario useraBuscar = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario:");
        userName = scanner.next();

        System.out.println("Ingrese su contraseña:");
        pass = scanner.next();

        useraBuscar = usuarioDao.findByUsername(userName);

        return useraBuscar;

    }
}
