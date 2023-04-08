package ventana_fichero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Ventana extends JFrame{
	
	private static final long serialVersionUID = -3662644634528230816L;

	public Ventana() {
		
		setBounds(600, 300, 700, 350);
	
		Lamina lamina = new Lamina();
		
		add(lamina);
		
		setVisible(true);
	
	}

}


class Lamina extends JPanel{
	
	private static final long serialVersionUID = 5621986215560144407L;

	public Lamina() {
		
		JLabel etiquetaCampo = new JLabel("Introduce el nombre del fichero: ");
		
			add(etiquetaCampo);
		
		JTextField campoTexto = new JTextField(20); 
			
			add(campoTexto);
			
		JTextArea areaTexto = new JTextArea(15, 50);
		
		//Agregamos un borde al área de texto
		Border borde = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	    areaTexto.setBorder(BorderFactory.createTitledBorder(borde, "Texto del fichero"));
		
			add(areaTexto);
		
		JButton boton = new JButton("Leer fichero");
		
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nombreFichero = "C:\\Users\\David\\Desktop\\" + campoTexto.getText();
				File archivo = new File(nombreFichero);
				
				if(archivo.exists()) {
					ManipularFicheros mf = new ManipularFicheros();
					String texto = mf.leer(archivo);
					areaTexto.setText(texto);
				}else {
					areaTexto.setText("El archivo no existe");
				}
			}
			
		});
			
			add(boton);
		}
		
}
