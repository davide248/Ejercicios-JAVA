
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Ahorcado {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {	
		
		char[] palabra = {'d', 'a', 'v', 'i', 'd'};
		
		int totalLetras = palabra.length;
		
		char[] intentoPalabra = new char[totalLetras];
		
		
		int opcion=0;
		int intentos = 3;
		boolean ganador = false;
		boolean salir = false;

		
		System.out.println("He pensado una la palabra de "+ totalLetras +" letras:");
		System.out.println("PISTA: nombre");
		
		while(!salir && !ganador) {
			System.out.println("\n1. Introducir letra");
			System.out.println("2. Resolver palabra");
			System.out.println("3. Salir");
			
			do {
				
				try {
					System.out.println("Introduce una opcion [1-3]");
					opcion = sc.nextInt();
				}catch(InputMismatchException e) {
					opcion=0;
					sc = new Scanner(System.in);
				}

				
			}while(opcion <= 0 || opcion > 3);
			
			switch (opcion) {
				case 1:
					System.out.println("Escoge una letra");
	
					char letraElegida = sc.next().charAt(0);
	
					aciertaLetra(palabra, letraElegida, intentoPalabra);
	
					intentos--;
	
					if (intentos <= 0) {
						System.out.println("HAS PERDIDO");
						salir = true;
					}
	
					if (Arrays.equals(palabra, intentoPalabra)) {
						System.out.println("\nENHORABUENA, HAS GANADO");
						ganador = true;
						salir = true;
					} else {
						System.out.println("\nTe quedan " + intentos + " intentos");
					}
	
					break;
				case 2:
					intentoPalabra = introducirPalabra();
	
					ganador = Arrays.equals(palabra, intentoPalabra);
	
					if (ganador) {
						System.out.println("\nENHORABUENA, HAS GANADO");
					} else {
						intentos = 0;
	
						if (intentos <= 0) {
							System.out.println("HAS PERDIDO");
							salir = true;
						}
					}
	
					break;
				case 3:
					System.out.println("Hasta la proxina!");
					salir = true;
					break;
				default:
					System.out.println("Opcion incorrecta. Introduce un numero entre 1 y 3.");
			}
		}
		
	}
	
	
	 static void aciertaLetra(char[] palabra, char letra, char[] aciertos) {
		
		for (int i = 0; i < palabra.length; i++) {
			if(palabra[i] == letra) {
				System.out.println("ACIERTO!! La letra " + palabra[i] + " se encuentra "
						+ "en la posicion " + (i+1));
				aciertos[i] = palabra[i];
			}
		}
		
		mostrarArray(aciertos);

	}
	
	
	static char[] introducirPalabra() {
		
		String palabra;
		
		System.out.println("Introduce la palabra: ");
		
		palabra = sc.next();
			
		return palabra.toCharArray();
	}
	
	
	 static void mostrarArray(char[] aciertos) {
		 
			for (int i = 0; i < aciertos.length; i++) {
				if(aciertos[i] == '\u0000') {
					aciertos[i] = '_';
				}
					System.out.print(aciertos[i] + " ");

			}
			System.out.println("");
	}
	 

}
