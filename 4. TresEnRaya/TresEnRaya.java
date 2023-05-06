import java.util.Scanner;
import java.util.Random;

public class TresEnRaya {
	
	static char[][] tablero;

	static boolean jugador;
	
	static Scanner sc = new Scanner(System.in);

	
	public TresEnRaya() {
		tablero = inicializarTablero();
		jugador = empiezaPrimero();
	}
	
	static char[][] inicializarTablero() {
		
		char[][] tablero = new char[3][3];
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j] = '-';
			}
		}
		
		return tablero;
		
	}
	
	static void mostrarTablero(char[][] tablero) {
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				System.out.print(tablero[i][j] + " "); 
			}
			System.out.println("");
		}
		
	}
	
	static boolean empiezaPrimero() {
		
		Random valor =  new Random();
		
		int num = (int)valor.nextInt(2);
		
		if(num == 0) {
			jugador = false;
			System.out.println("Empieza el jugador 2");
		}else{
			jugador = true;
			System.out.println("Empieza el jugador 1");
		}
		
		return jugador;
	}
	
	static boolean cambiaTurno() {
		
		if(jugador == false) {
			jugador = true;
			System.out.println("Turno del jugador 1");
		}else{
			jugador = false;
			System.out.println("Turno del jugador 2");
		}

		return jugador;
	}
	
	static boolean validarPosicion(int[] pos) {
		
		boolean correcto = true;
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if(i == pos[0] && j == pos[1]) {
					if(tablero[i][j] == 'o' || tablero[i][j] == 'x') {
						correcto = false;
					}
				}
			}
		}
		
		return correcto;
		
	}
	
	
	static int[] pedirPosicion() {
		
		int fila, columna;
		
		int[] pos = new int[2];
		
		boolean posicionValida;

		do {
			System.out.println("Introduce la fila:");
			
			fila = sc.nextInt();

		    pos[0] = fila-1;
		    
			System.out.println("Introduce la columna:");
			
			columna = sc.nextInt();

		    pos[1] = columna-1;
		    
		    posicionValida = validarPosicion(pos);
		    
			    if(!posicionValida) {
			    	System.out.println(posicionValida);
			    	System.out.println("La posición está ocupada, vuelve a probar");
			    }
		    
		}while(!posicionValida);
		
		return pos;
	}
	
	
	static boolean comprobarFila() {

	    boolean linea = false;

	    for (int i = 0; i < tablero.length; i++) {
	        int contadorX = 0, contadorO = 0;
	        
	        for (int j = 0; j < tablero[i].length; j++) {
	            char celda = tablero[i][j];
	            
	            if (celda == 'x') {
	                contadorX++;
	            } else if (celda == 'o') {
	                contadorO++;
	            }
	        }
	        
	        if (contadorX == 3 || contadorO == 3) {
	            linea = true;
	        } 
	    }

	    return linea;
	}
	
	
	static boolean comprobarColumna() {

	    boolean linea = false;

	    for (int j = 0; j < tablero.length; j++) {
	        int contadorX = 0, contadorO = 0;
	        
	        for (int i = 0; i < tablero.length; i++) {
	            char celda = tablero[i][j];
	            
	            if (celda == 'x') {
	                contadorX++;
	            } else if (celda == 'o') {
	                contadorO++;
	            }
	        }
	        
	        if (contadorX == 3 || contadorO == 3) {
	            linea = true;
	        } 
	    }

	    return linea;
	}
	
	
	static boolean comprobarDiagoPrin() {
		
		boolean linea = false;
	
		int i=0, j=0, contadorX = 0, contadorO = 0;
		
		while(j<3 && i<3 && (i == j)) {
			
            char celda = tablero[i][j];
            
            if (celda == 'x') {
                contadorX++;
            } else if (celda == 'o') {
                contadorO++;
            }
            
	        if (contadorX == 3 || contadorO == 3) {
	            linea = true;
	        } 
            
			j++;
			i++;
		}
	
	    return linea;
    }
	
	
	static boolean comprobarDiagoInv() {
		
		boolean linea = false;
		
		int contadorX = 0, contadorO = 0;
	
		for (int j = 0, i=2; j < tablero.length; j++,i--) {
			
			while(j<3 && j<3 && ((j+i) == 2)) {
	            char celda = tablero[i][j];
	            
	            if (celda == 'x') {
	                contadorX++;
	            } else if (celda == 'o') {
	                contadorO++;
	            }
	            
				j++;
				i--;
			}
				
	        if (contadorX == 3 || contadorO == 3) {
	            linea = true;
	        } 
        
		}
	
	    return linea;
	
   }
		
	
	 void juego() {
		
		int[] pos = new int[2];
		
		do {
			
			pos = pedirPosicion();
			
			if(jugador == true) {
				tablero[pos[0]][pos[1]] = 'x';
			}else{
				tablero[pos[0]][pos[1]] = 'o';
			}
			
			mostrarTablero(tablero);
			
			if(!finJuego()) {
				System.out.println("");
				cambiaTurno();
			}
			
			
		}while(!finJuego());
		
		if(finJuego()) {
			if(jugador == false) {
				System.out.println("ENHORABUENA HAS GANADO JUGADOR 2");
			}else{
				System.out.println("ENHORABUENA HAS GANADO JUGADOR 1");
			}
		}

		
	}
	
	static boolean finJuego() {
		
		boolean fin = false;
		
		if(comprobarFila() || comprobarColumna() || comprobarDiagoPrin() || comprobarDiagoInv()) {
			
			fin = true;
			
		}
		
		return fin;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Bienvenido al juego del 3 en raya\n");
		
		TresEnRaya partida = new TresEnRaya();		
		
		mostrarTablero(tablero);
		
		partida.juego();

	}

}
