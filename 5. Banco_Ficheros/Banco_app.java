import java.util.Scanner;

public class Banco_app {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		/*
		 * MENU BANCO
		 * ¿Qué deseas hacer?
		 * 1. Crear cuenta
		 * 2. Comprobar saldo
		 * 3. Sacar dinero
		 * 4. Ingresar dinero
		 * 5. Realizar transferencias
		 * 6. Ver todas las cuentas
		 * 7. Borrar cuenta
		 * 8. Salir
		 * */
		
		
		Banco BBVA = new Banco();
		
		int decision;
		char continuar;
		Cuenta cuenta, cuenta2;
		double cantidad;
		
		do {
			decision = leerDecision();
			continuar = 'S';
			
			switch(decision) {
				case 1:
					BBVA.agregarCuenta();
					break;
					
				case 2:
					cuenta = pedirCuenta(BBVA);
					
					if(cuenta != null) {
						System.out.println(cuenta.getSaldo() + " €");
					}else {
						System.out.println("El número de cuenta indicado no existe");
					}
					break;
					
				case 3:
					cuenta = pedirCuenta(BBVA);
					
					if(cuenta != null) {
						cantidad = pedirCantidad();
						cuenta.retirar(cantidad);
						BBVA.modificarCuenta(cuenta);
					}else {
						System.out.println("El número de cuenta indicado no existe");
					}
					break;
					
				case 4:
					cuenta = pedirCuenta(BBVA);
					
					if(cuenta != null) {
						cantidad = pedirCantidad();
						cuenta.ingresar(cantidad);
						BBVA.modificarCuenta(cuenta);
					}else {
						System.out.println("El número de cuenta indicado no existe");
					}
					break;
					
				case 5:
					cuenta = pedirCuenta(BBVA);
					cuenta2 = pedirCuenta(BBVA);
					
					if(cuenta != null && cuenta2 != null) {
						cantidad = pedirCantidad();
						BBVA.transferencia(cuenta, cuenta2, cantidad);
						BBVA.modificarCuenta(cuenta);
						BBVA.modificarCuenta(cuenta2);
					}else {
						System.out.println("El número de cuenta indicado no existe");
					}
					break;
					
				case 6:		
					BBVA.mostrarCuentas();
						
					break;
					
				case 7:		
					cuenta = pedirCuenta(BBVA);
					
					if(cuenta != null) {
						
						BBVA.borrarCuenta(cuenta);
						
					}else {
						System.out.println("El número de cuenta indicado no existe");
					}
						
					break;
					
				case 8:
					continuar = 'N';
					break;
			}
			
			
			if(decision != 8) {
				continuar = leerContinuar();
			}
			
		}while(continuar == 'S');
		
		System.out.println("¡Hasta pronto!");
		
		sc.close();

	}
	
	public static int leerDecision() {
		
		int decision;
		
		System.out.println(" MENU BANCO\n ¿Qué deseas hacer?\n 1. Crear cuenta\n 2. Comprobar saldo\n 3. Sacar dinero\n 4. Ingresar dinero "
				+ "\n 5. Realizar transferencias\n 6. Ver todas las cuentas\n 7. Borrar cuenta \n 8. Salir");
		
		decision = sc.nextInt();
		
		while(decision < 1 || decision > 8) {
			
			System.out.println("Comando inválido\n Qué deseas hacer?\n 1. Crear cuenta\n 2. Comprobar saldo\n 3. Sacar dinero\n 4. Ingresar dinero "
					+ "\n 5. Realizar transferencias\n 6. Ver todas las cuentas\n 7. Borrar cuenta\n 8. Salir");
			
			decision = sc.nextInt();
			
		}
				
		return decision;
		
	}
	
	public static char leerContinuar() {
		
		char continuar;
		
		System.out.println("¿Deseas realizar otra operacion? (S/N)");
		
		continuar = sc.next().charAt(0);
		
		continuar = Character.toUpperCase(continuar);

		while(continuar != 'S' && continuar != 'N') {
			
			System.out.println("Comando inválido. Debes introducir S o N");
			
			continuar = sc.next().charAt(0);
			
			continuar = Character.toUpperCase(continuar);
			
		}
				
		return continuar;
		
	}
	
	public static Cuenta pedirCuenta(Banco b) {
		
		String numCuenta;
		
		System.out.println("Introduce tu número de cuenta");
		numCuenta = sc.next();
		
		return b.comprobarCuenta(numCuenta);
		
	}
	
	public static double pedirCantidad() {
		
		double cantidad;
		
		System.out.println("Introduce la cantidad");
		cantidad = sc.nextDouble();
		
		while(cantidad < 0) {
			System.out.println("La cantidad ha de ser postiva.\n Introduce la cantidad");
			cantidad = sc.nextDouble();
		}
		
		return cantidad;
		
	}

}
