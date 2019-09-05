package arem.proyecto;

import java.lang.reflect.Method;


public class UrlHandler implements Handler{
    private Method method;

    public UrlHandler(Method method){
        try {
            this.method= method;    
        } catch (Exception e) {
            System.out.println("Error Aqui en UrlHandler");
        }       

    }

    public String procesar(){
        System.out.println("entraaaaaaaaaaaaaaaaaaaaaaa");
        try{
             return (String) method.invoke(null,null);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
