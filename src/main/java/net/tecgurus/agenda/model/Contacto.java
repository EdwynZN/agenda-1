package net.tecgurus.agenda.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope("prototype")
public @Data class Contacto {
	private Integer id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String email;
	private Date fechaNacimiento;
	private Integer id_usuario;
	private String imagen;
	
	public Integer getEdad(){
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    int cumple = Integer.parseInt(formatter.format(this.fechaNacimiento));
	    int hoy = Integer.parseInt(formatter.format(new Date()));
	    int edad = (hoy-cumple)/10000;
	    return edad;
	}
}
