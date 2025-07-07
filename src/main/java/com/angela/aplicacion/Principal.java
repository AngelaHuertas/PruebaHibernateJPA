package com.angela.aplicacion;

import com.angela.vista.VentanaMascota;

public class Principal {
	public static void main(String[] args) {
		/*Aplicacion miAplicacion=new Aplicacion();
		miAplicacion.iniciar();*/
		
		VentanaMascota frame = new VentanaMascota();
		frame.setVisible(true);
	}
}
