package com.retailjavafinal.controllers;

import com.retailjavafinal.dao.CompraDao;
import com.retailjavafinal.models.Compra;
import com.retailjavafinal.models.Usuario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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


}
