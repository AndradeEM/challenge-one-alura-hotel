package com.jdbc.modelo;

import java.sql.Date;

public class Reserva {
	
	private Integer id;
	private Date FechaEntrada;
	private Date FechaSalida;
	private String valor;
	private String formaPago;
	
	public Reserva(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		this.FechaEntrada = fechaEntrada;
		this.FechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		this.id = id;
		this.FechaEntrada = fechaEntrada;
		this.FechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getFechaEntrada() {
		return FechaEntrada;
	}
	
	public Date getFechaSalida() {
		return FechaSalida;
	}
	
	public String getValor() {
		return valor;
	}
	
	public String getFormaPago() {
		return formaPago;
	}

}
