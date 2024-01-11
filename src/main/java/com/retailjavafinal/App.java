package com.retailjavafinal;

import com.retailjavafinal.models.Compra;
import com.retailjavafinal.models.DetalleCompra;
import com.retailjavafinal.models.Producto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class App {
    public static void main( String[] args )
    {
        System.out.println( "Bienvenido/a al sistema de retail" );
        //Prueba al crear modelos y fecha
        Compra compra = new Compra();
        Producto producto = new Producto();
        DetalleCompra detalle = new DetalleCompra(2,100,200,new Date(2023,12,31),producto,compra);

        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("detalle de compra prueba solo modelos, fecha: " + detalle.getFecha_trx() + ", precio: " + detalle.getPrecio());

        System.out.println("fecha de prueba " + date);
    }
}
