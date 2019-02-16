package net.tecgurus.agenda.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope("prototype")
public @Data class Usuario {
	private Integer id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String email;
	private String pass;
	private Date fechaNacimiento;	
}