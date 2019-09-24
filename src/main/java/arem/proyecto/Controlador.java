package arem.proyecto;

import arem.Clases_pruebas.*;
import java.io.File;
import java.lang.reflect.Method;

public class Controlador {

    /**
     * clase principal de todo el modelo encargado de leer en la Clase Prueba1 los metodos y de iniciar el appServer
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            String p = "arem.Clases_pruebas.";

            File carpeta = new File(System.getProperty("user.dir")+"//src//main//java//arem//Clases_pruebas");
            String[] listado = carpeta.list();
            
            for (int i = 0; i < listado.length; i++) {
                String urlInputLine="";
                int j=0;                
                if(listado[i].contains(".java")){
                    while (j < listado[i].length()-5) {
                        urlInputLine += (listado[i].charAt(j));
                        j+=1;
                    }
                    Class c = Class.forName(p + urlInputLine);
                    for (Method m : c.getMethods()) {                    
                        if (m.isAnnotationPresent(Web.class)) {
                            Handler h = new UrlHandler(m);                            
                            AppServer.appendHash("/apps/" + m.getAnnotation(Web.class).value(),m);
                        }
                    }
                }                    
            }  
                      
        } catch (Exception e) {
            e.printStackTrace();

        }
        balanceador.main(args);            
    }
}
