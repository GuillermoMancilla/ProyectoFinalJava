package com.retailjavafinal.controllers;

import com.retailjavafinal.dao.CompraDao;
import com.retailjavafinal.models.Compra;
import com.retailjavafinal.models.Usuario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CompraController {

    public void creacompra(){
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Compra compra = new Compra(date,1000);

        CompraDao compraDao = new CompraDao();
        compraDao.insert(compra);
    }

    public void consultaTodasLasComprasDeusuario(Usuario usuario){
        CompraDao compraDao = new CompraDao();
        List<Compra> compras = compraDao.findAllofUserCpas(usuario.getId());

        for (Compra cpacle: compras){
            System.out.println("\ncompra numero: " + cpacle.getid() + "\npor un total de: " + cpacle.getTotal()+"\n");
        }
    }

    public void despacho() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indique el tipo de envio:\n1. Despacho a domicilio\n2. Retiro en tienda");
        int seleccion = scanner.nextInt();

        switch (seleccion) {
            case 1:
                System.out.println("El envio sera a domicilio dentro de 5 días habiles");
                break;
            case 2:
                System.out.println("El retiro en tienda sera dentro de los 4 días habiles");
                break;
            default:
                System.out.println("Opcion no valida. Intentalo de nuevo.");
        }
    }

}
