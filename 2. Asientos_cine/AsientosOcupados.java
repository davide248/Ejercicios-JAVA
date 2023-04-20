import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AsientosOcupados {
	
    static Random valor =  new Random();
	  static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		int[][] sala = rellenarSala();
		
		int numEntradas;
		
		mostrarSala(sala);
		
		numEntradas = pedirEntradas();
		
		rellenarFila(sala, numEntradas);
		
		mostrarSala(sala);

	}
	
	public static int[][] rellenarSala () {
		
		int[][] lista = new int[10][20];
		
		for (int i = 0; i < lista.length; i++) {
			for(int j = 0; j < lista[0].length; j++) {
				lista[i][j] = valor.nextInt(2);
			}	
		}
		
		return lista;
		
	}//fin rellenar
	
	
	public static void mostrarSala (int[][] lista) {
		
		for (int i = 0; i < lista.length; i++) {
			for(int j = 0; j < lista[0].length; j++) {
				System.out.print(lista[i][j] + " ");
			}
			System.out.println("");
		}
	}//fin mostrar
	
	
	public static int pedirEntradas() {
		
		int numEntradas = 0;
		boolean correcto = false;
		
		System.out.println("Cuantas entradas necesitas?");
		
		do {

			try {
				 numEntradas = sc.nextInt();
				 
				 if(numEntradas >0 && numEntradas < 10) {
					 correcto = true;
				 }else {
					 System.out.println("Introduce una cantidad de entradas entre 0 y 10");
				 }
			}catch(InputMismatchException e) {
				System.out.println("Debes introducir un valor numerico");
				break;
			}
			

		}while(correcto == false);

		
		sc.close();
		
		return numEntradas;
		
	}

	
	public static void rellenarFila(int[][] lista, int num) {
		
		int cuentaCeros=0, cuentaMaxCeros=0, fila=0, columna=0;
		
		boolean salir = false;
		
		for (int i = 0; i < lista.length && !salir ; i++) {
			for(int j = 0; j < lista[0].length && !salir; j++) {
				if(lista[i][j] == 0) {
					while(i<lista.length && j<lista[0].length && lista[i][j] == 0) {
						cuentaCeros++;
						j++;
						if(cuentaCeros > cuentaMaxCeros) {
							cuentaMaxCeros = cuentaCeros;
						}
						
						if(cuentaMaxCeros >= num) {
							fila = i;
							columna = j-1;	
							salir = true;
						}

					}

					cuentaCeros = 0;				
				}	
			}
		}

		//Rellenamos con "2" los huecos que pasan a estar ocupados
		for (int j = columna; j > columna - num; j--) {
			lista[fila][j] = 2;
		}
		
		//Informamos de la fila
		if(fila>=0) {
			System.out.println("Hay " + num + " huecos seguidos en la fila " + fila+1);
		}else {
			System.out.println("Lo sentimos, no disponemos de tantos asientos vacios seguidos");
		}
		
	}//fin rellenar fila con 2
	

}
