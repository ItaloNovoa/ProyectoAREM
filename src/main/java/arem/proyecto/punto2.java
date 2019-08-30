package arem.proyecto;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class punto2 {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://www.google.com/");


		FileWriter fichero = null;
		PrintWriter pw = null;
		try
		{
                        //ingrese ubicación de destino del html
			fichero = new FileWriter("C:\\Users\\2135142\\Documents\\pag\\resultado.html");
			pw = new PrintWriter(fichero);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {					
					pw.println( inputLine);
				}				
			} catch (IOException x) {
				System.err.println(x);				

			} }catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {

					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}		
	}
}