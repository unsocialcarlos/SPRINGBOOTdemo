package com.example.demo;

import com.example.demo.models.Libro;
import com.example.demo.models.Producto;
import com.example.demo.models.UserData;
import com.example.demo.myBeans.MiBean;
import com.example.demo.myBeans.MiComponente;
import com.example.demo.servicios.IOrderService;
import com.example.demo.servicios.OrderService;
import jdk.jfr.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Rutas {

    private IOrderService orderService;
    private MiBean miBean;
    private MiComponente miComponente;

    public Rutas (IOrderService orderService, MiBean miBean, MiComponente miComponente) {
        this.orderService = orderService;
        this.miBean = miBean;
        this.miComponente = miComponente;
    }

    private final Logger logger = LoggerFactory.getLogger(Rutas.class);

    @GetMapping("/hola")
    String miPrimeraRuta() {
        return "Hola Mundo desde Spring Controller";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial) {
        return "Leyendo el libro id: " + id + " ,editorial: " + editorial;
    }

    @GetMapping("/libro2/{id}")
    String leerLibro2(@PathVariable int id, @RequestParam String gato, @RequestParam String editorial) {
        return "Leyendo el libro id: " + id + ", params: " + gato + ", editorial: " + editorial;
    }

    @PostMapping("/libro")
    String guardarLibro(@RequestBody Libro libro){

            logger.debug("libro {} editorial {} ", libro.nombre, libro.editorial);
            //if (libro.nombre == null) throw new IllegalArgumentException("Nombre del libro no puede ser nulo");
        return "Libro guardado";
    }

    @GetMapping("/saludar")
    @ResponseStatus(value= HttpStatus.MOVED_PERMANENTLY, reason = "Fue movida a la versión 2 de la API")
    String miSegundaRutaConStatus() {
        return "Aprendiendo status en SpringBoot";
    }

    @GetMapping("/animales/{lugar}")
    public ResponseEntity<String> getAnimal(@PathVariable String lugar) {
        if (lugar.equals("granja")) {
            return ResponseEntity.status(HttpStatus.OK).body("Caballo, vaca, obeja, gallina");
        } else if (lugar.equals("selva")) {
            return ResponseEntity.status(HttpStatus.OK).body("Mono, gorila, puma");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El lugar no existe");
        }
    }

    @GetMapping ("/calcular/{numero}")
    public int getCalculo(@PathVariable int numero){
        throw new NullPointerException("Contraseña del usuario pass= 123. No deberia leerse");
    }

    @GetMapping("/userData")
    public ResponseEntity <String> getUserData() {

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"name\": \"mary\"}");
    }

    @GetMapping("/userData/v2")
    public Map <String, Map<String, Object>> getUserDataV2() {

        return Map.of("user", Map.of("name", "mary", "age", 25));
    }

    @GetMapping("/userData/v3")
    public UserData getUserDataV3() {
        return new UserData("mary", 25, "alameda 1234");
    }

    @PostMapping ("/order")
    public String crearOrden(@RequestBody List<Producto> products) {
        orderService.saveOrder(products);
        return "Productos guardados";
    }

    @GetMapping ("/mibean")
    public String saludarDesdeBean() {
        return miBean.saludar();
    }

    @GetMapping ("/micomponente")
    public String saludarDesdeMiComponente() {
        miComponente.saludarDesdeComponente();
        return "Completado desde componente";
    }
}
