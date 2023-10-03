package com.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.controller.ReservasController;
import com.jdbc.modelo.Huespedes;

public class HuespedesDAO {
	
	private Connection connection;
	
	public HuespedesDAO(Connection connection) {
		this.connection = connection;
		
	}
	
	public void guardar(Huespedes huesped) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) VALUES (?, ?, ?, ?, ?, ?)";
		
			try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
				stm.setString(1, huesped.getNombre());
				stm.setString(2, huesped.getApellido());
				stm.setDate(3, huesped.getFechaNacimiento());
				stm.setString(4, huesped.getNacionalidad());
				stm.setString(5, huesped.getTelefono());
				stm.setInt(6, huesped.getIdReserva());
			
				stm.executeUpdate();
			
				try (ResultSet result = stm.getGeneratedKeys()) {
					while (result.next()) {
						huesped.setId(result.getInt(1));
					
					}
				}
			}
			
		} catch (SQLException e) {		
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Huespedes> buscar() {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespedes";
			
			try (PreparedStatement stm = connection.prepareStatement(sql)) {
				stm.execute();
				
				transformarResultSetEnHuesped(huespedes, stm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Huespedes> buscarId(String id) {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespedes WHERE idReserva = ?";
			
			try (PreparedStatement stm = connection.prepareStatement(sql)) {
				stm.setString(1, id);
				stm.execute();
				
				transformarResultSetEnHuesped(huespedes, stm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEnHuesped(List<Huespedes> reservas, PreparedStatement stm) throws SQLException {
		try (ResultSet result = stm.getResultSet()) {
			while (result.next()) {
				Huespedes huespedes = new Huespedes(result.getInt(1), result.getString(2), result.getString(3), result.getDate(4), result.getString(5), result.getString(6), result.getInt(7));
				
				reservas.add(huespedes);
			}
			
		}
		
	}

	public void Eliminar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM huespedes WHERE id = ?")){
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("UPDATE huespedes SET nombre = ?, apellido = ?, fechaNacimiento = ?, nacionalidad = ?, telefono = ?, idReserva = ? WHERE id = ?")) {
			
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setDate(3, fechaNacimiento);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	