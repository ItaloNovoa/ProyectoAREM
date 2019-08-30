package arem.proyecto;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class UrlHandler 
{
    URL url ;

    public UrlHandler(String url){
        try {
            this.url= new URL(url);    
        } catch (MalformedURLException e) {
            //TODO: handle exception
        }       

    }

}
