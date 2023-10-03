package com.jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.dao.HuespedesDAO;
import com.jdbc.factory.ConnectionFactory;
import com.jdbc.modelo.Huespedes;

public class HuespedesController {
	
	private HuespedesDAO huespedDAO;
	
	public HuespedesController() throws SQLException {
		Connection connection = new ConnectionFactory().createConnection();
		this.huespedDAO = new HuespedesDAO(connection);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedDAO.guardar(huespedes);
	}
	
	public List<Huespedes> listarHuespedes(){
		return this.huespedDAO.buscar();
	}
	
	public List<Huespedes> listarHuespedesId(String id){
		return this.huespedDAO.buscarId(id);
	}
	
	public void actualizar(String nombre, String apellido, Date fechaNAcimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		this.huespedDAO.Actualizar(nombre, apellido, fechaNAcimiento, nacionalidad, telefono, idReserva, id);
	}
	
	public void Eliminar(Integer id) {
		this.huespedDAO.Eliminar(id);
	}
}
