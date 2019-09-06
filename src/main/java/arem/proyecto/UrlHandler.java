package arem.proyecto;

import java.lang.reflect.Method;

/**
 * clase encargada de manejar los parametros y metodos de las clases pruebas @Web
 */
public class UrlHandler implements Handler{
    private Method method;

    /**
     * asignacion de metodo a Objeto
     * @param method
     */
    public UrlHandler(Method method){
        try {
            this.method= method;    
        } catch (Exception e) {
            System.out.println("Error Aqui en UrlHandler");
        }       

    }
    /**
     * metodo encargado de invocar el metodo asignado a la clase cuando no hay parametros
     */
    public String procesar(){
        try{
             return (String) method.invoke(null,null);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
    /**
     * metodo encargado de invocar el metodo asignado a la clase cuando hay parametros
     */
    @Override
    public String procesar(Object[] arg) throws Exception {
        try{
            return (String) method.invoke(method, arg);
        }catch(Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}
