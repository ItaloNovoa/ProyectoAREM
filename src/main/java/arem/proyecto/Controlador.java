package arem.proyecto;

import java.io.IOException;
import java.lang.reflect.Method;

public class Controlador {

    /**
     * clase principal de todo el modelo encargado de leer en la Clase Prueba1 los metodos y de iniciar el appServer
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            String p = "arem.proyecto.";
            Class c = Class.forName(p + "prueba1");

            for (Method m : c.getMethods()) {                    
                if (m.isAnnotationPresent(Web.class)) {
                    Handler h = new UrlHandler(m);
                    AppServer.appendHash("/apps/" + m.getAnnotation(Web.class).value(),m);
                }
            }
        } catch (Exception e) {                
            System.out.println("Error");
            e.printStackTrace();

        }
        AppServer.main(args);      
    }
}
