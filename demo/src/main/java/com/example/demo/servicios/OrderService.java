package com.example.demo.servicios;

import com.example.demo.Rutas;
import com.example.demo.models.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService implements IOrderService {

    @Value("${misurls.database.datawarehouse}")
    private String databaseUrl;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void saveOrder(List<Producto>products){
        System.out.println("Guardando en la base de datos, la url:" + databaseUrl);

        products.forEach(producto -> logger.debug("El nombre del producto: {}", producto.nombre));
    }

}
