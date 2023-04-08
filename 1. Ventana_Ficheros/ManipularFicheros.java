package ventana_fichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ManipularFicheros {
	
	public String leer(File fichero) {
		
		String texto = "";
		
		if(fichero.exists()) {
			try {
				FileReader fr = new FileReader(fichero); //flujo de lectura
				
				BufferedReader br = new BufferedReader(fr); //almacenamos el flujo el un buffer
				
				String linea;
				
				while((linea = br.readLine()) != null) {
					
					texto += linea + " \n";
					
				}
				
				br.close();
			}catch(IOException e) {
				System.out.println("Error al leer el fichero");
			}
		}else {
			System.out.println("El fichero no existe");
		}
		
		return texto;
		
	}
	

}
