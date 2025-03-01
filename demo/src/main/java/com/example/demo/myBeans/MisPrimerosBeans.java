package com.example.demo.myBeans;

import com.example.demo.models.Producto;
import com.example.demo.servicios.IOrderService;
import com.example.demo.servicios.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MisPrimerosBeans {

    @Bean
    public MiBean crearMiBean() {
        return new MiBean();
    }

    @Bean
    public IOrderService instanciarOrderService() {
        boolean esProduccion = false;
        if(esProduccion) {
            return new OrderService();
        }{
            return new IOrderService() {
                @Override
                public void saveOrder(List<Producto> products) {
                    System.out.println("Guardando en base de datos dummy (local)");

                }
            };
        }
    }
}
