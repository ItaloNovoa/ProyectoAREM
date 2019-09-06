package arem.proyecto;

public interface Handler{

    public String procesar();

    public  String procesar(Object[] arg)  throws Exception;
}