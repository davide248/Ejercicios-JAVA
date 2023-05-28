import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Banco {
	
	static Scanner sc = new Scanner(System.in);
	
	static ArrayList <Cuenta> cuentas;
	private File fichero = new File ("C:\\Users\\David\\Desktop\\cuentas.txt");
	
	public Banco() {
		cuentas = new ArrayList<Cuenta>();
	}
	
	public void agregarCuenta() {
		
		String nombre;
		String numero;
		double saldo;
		
		char opcion;
		boolean continuar = true;
	
		
		if(fichero.exists()) {
			try {
				
				ObjectOutputStream oos;
				
				do{	
					
					System.out.println("Introduce el nombre del titular");
					nombre = sc.next();
					
					System.out.println("Introduce el numero de cuenta");
					numero = sc.next();
					
					System.out.println("Introduce el saldo de apertura");
					saldo = sc.nextDouble();
					
					/*Comprobamos si el la primera vez que añadimos e instanciamos la clase genérica ObjectOutputStream
					 *o la nuestra propia en caso de ya haber registro para no sobrescribir la cabecera*/
					if(fichero.length() == 0) {	  
						
						    oos = new ObjectOutputStream(new FileOutputStream(fichero));
						 
	                        oos.writeObject(new Cuenta(nombre, numero, saldo));
	                        
	                        System.out.println("La cuenta se agregó correctamente");
	                        
	            	}else {
	            		
	            		 oos = new MyClaseAnadir(new FileOutputStream(fichero, true));
	            		 
	            		 Cuenta cuenta = comprobarCuenta(numero);
	            		 
	 		            if (cuenta == null) {
	 		            	  
	                        oos.writeObject(new Cuenta(nombre, numero, saldo));
	                        
	                        System.out.println("La cuenta se agregó correctamente");
	                        
	                    } else {
	                        System.out.println("La cuenta ya existe");
	                    }
	            	}
                     
                    oos.close();
					
					System.out.println("¿Deseas introducir otra cuenta? (S/N)");
					
					opcion = sc.next().charAt(0);
					opcion = Character.toUpperCase(opcion);
					
					if(opcion == 'N') {
						continuar = false;
					}

				}while(continuar);
			
                oos.close();

				
			} catch (IOException e) {
				System.out.println("Fichero no encontrado");
			} 
		}else {
			try {
				fichero.createNewFile(); // si el archivo no existe, se crea uno nuevo
				System.out.println("Se ha creado el archivo " + fichero.getName());
				agregarCuenta(); // se llama de nuevo el método para escribir en el archivo
			}catch(IOException e) {
				System.out.println("Error al escribir el fichero");
			}
		}
	}
	
	
	public Cuenta comprobarCuenta(String num) {
		
		Cuenta cuenta = null;
		
		for(Cuenta c: leerCuentas()) {
			if(c.getNumCuenta().equals(num)) {
				cuenta = c;
				
			}
		}
		
		return cuenta;
	}
	
	//Transferencia
	public void transferencia(Cuenta c1, Cuenta c2, double cantidad) {

			if(c1.getSaldo() >= cantidad) {
				c1.retirar(cantidad);
				c2.ingresar(cantidad);
			}
	}
	
	
	public ArrayList<Cuenta> leerCuentas() {
	    ArrayList<Cuenta> cuentas = null;
	    
	    try {
	        FileInputStream fis = new FileInputStream(fichero);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        
	        cuentas = new ArrayList<>();
	        
	        boolean seguirLeyendo = true;
	        
	        while(seguirLeyendo) {
	        	try {
	        		cuentas.add((Cuenta)ois.readObject());
	        	} catch (EOFException e) {
	    	    	seguirLeyendo = false;
	    	    }  
	        }
	        ois.close();
	    }catch (IOException  e) {
	    	System.out.println("Fichero no encontado o corrupto");
	    }catch (ClassNotFoundException e) {

	    }
	    
	    return cuentas;
	}
	
	
	public void modificarCuenta(Cuenta c) {
	    ArrayList<Cuenta> cuentas = leerCuentas();

	    for(int i=0; i<cuentas.size(); i++) {
	        if(cuentas.get(i).getNumCuenta().equals(c.getNumCuenta())) {
	            cuentas.set(i, c);
	        }
	    }

	    try {
	        FileOutputStream fos = new FileOutputStream(fichero);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        for(Cuenta cuenta : cuentas) {
	            oos.writeObject(cuenta);
	        }
	        oos.close();
	    } catch (IOException e) {
	        
	    }
	}
	
	
	public void borrarCuenta(Cuenta c) {
		
	    ArrayList<Cuenta> cuentas = leerCuentas();

	    Iterator<Cuenta> it = cuentas.iterator();
	    
	    while(it.hasNext()) {
	    	
	    	Cuenta cuenta = it.next();
	    	
	        if(cuenta.getNumCuenta().equals(c.getNumCuenta())) {
	            it.remove();
	        }
	    	
	    }

	    try {
	        FileOutputStream fos = new FileOutputStream(fichero);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        for(Cuenta cuenta : cuentas) {
	            oos.writeObject(cuenta);
	        }
	        oos.close();
	        
	        System.out.println("La cuenta " + c.getNumCuenta() + " se ha eliminado correctamente");
	        
	    } catch (IOException e) {
	        
	    }
	}

	
	public void mostrarCuentas() {
		
		try {
			for(Cuenta c: leerCuentas()) {
				System.out.println(c + "\n");
			}
		}catch (NullPointerException e) {
	    	System.out.println("No hay cuentas que mostrar");
	    }

		
	}

}
