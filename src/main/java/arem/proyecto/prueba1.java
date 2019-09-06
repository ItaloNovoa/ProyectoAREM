package arem.proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;


public class prueba1 {

     
    @Web("prueba1")
    public static String prueba() {
        return "primera prueba";
    }

    
    @Web("Nombre")
    public static String holaName(String name) {
        return "hola -->"+name;
    }

    @Web("Cuadrado")
    public static String suma(String n1) {
        int a=Integer.parseInt(n1);
        return "el cuadrado de "+n1+" es "+Integer.toString(a*a);
    }
}