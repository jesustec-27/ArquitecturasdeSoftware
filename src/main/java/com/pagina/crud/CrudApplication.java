package com.pagina.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de entrada para la aplicación Spring Boot.
 * <p>
 * Esta clase contiene el método main que arranca el servidor embebido (Tomcat)
 * e inicializa el contexto de Spring.
 * </p>
 *
 * @version 1.0
 */
@SpringBootApplication
public class CrudApplication {

    /**
     * Método principal que ejecuta la aplicación.
     *
     * @param args Argumentos de línea de comandos pasados a la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

}