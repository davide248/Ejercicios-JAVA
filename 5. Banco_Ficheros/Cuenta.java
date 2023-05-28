import java.io.Serializable;

public class Cuenta implements Serializable{

	private static final long serialVersionUID = 7213353907697172312L;
	
	private String nomCliente;
	private String numCuenta;
	private double saldo;
	
	public Cuenta(String nombre, String numero, double saldo) {
		this.nomCliente = nombre;
		this.numCuenta = numero;
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void ingresar(double cantidad) {
		if(saldo <= 0) {
			System.out.println("El ingreso debe ser positivo");
		}else {
			this.saldo += cantidad;
		}
	}
	
	public void retirar(double cantidad) {
		if(this.saldo <= cantidad) {
			System.out.println("No dispones de tanto dinero");
		}else {
			this.saldo -= cantidad;
		}
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	@Override
	public String toString() {
		return  "--> Cliente: " + nomCliente + 
				"\n--> Nº Cuenta: " + numCuenta + 
				"\n--> Saldo: " + saldo;
	}
	
}
