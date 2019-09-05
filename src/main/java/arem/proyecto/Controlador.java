package arem.proyecto;

import java.io.IOException;
import java.lang.reflect.Method;

public class Controlador {


    public static void main(String[] args) throws IOException {
        AppServer.main(args);
            
            try {
                String p = "arem.proyecto.";
                Class c = Class.forName(p + "prueba1");

                for (Method m : c.getMethods()) {

                    if (m.isAnnotationPresent(Web.class)) {
                        Handler h = new UrlHandler(m);
                        AppServer.appendHash("/apps/" + m.getAnnotation(Web.class).value(), h,m);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();

            }
       
    }

}
