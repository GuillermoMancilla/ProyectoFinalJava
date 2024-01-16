package com.retailjavafinal.controllers;

import com.retailjavafinal.dao.CompraDao;
import com.retailjavafinal.models.Compra;

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

    public void consultaTodasLasCompras(){
        CompraDao compraDao = new CompraDao();
        List<Compra> compras = compraDao.findAll();
    }


}
