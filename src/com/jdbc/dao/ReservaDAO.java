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
import com.jdbc.modelo.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
		
	}
	
	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?, ?, ?, ?)";
		
			try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
				stm.setDate(1, reserva.getFechaEntrada());
				stm.setDate(2, reserva.getFechaSalida());
				stm.setString(3, reserva.getValor());
				stm.setString(4, reserva.getFormaPago());
			
				stm.executeUpdate();
			
				try (ResultSet result = stm.getGeneratedKeys()) {
					while (result.next()) {
						reserva.setId(result.getInt(1));
					
					}
				}
			}
			
		} catch (SQLException e) {		
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas";
			
			try (PreparedStatement stm = connection.prepareStatement(sql)) {
				stm.execute();
				
				transformarResultSetEnReserva(reservas, stm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas WHERE id = ?";
			
			try (PreparedStatement stm = connection.prepareStatement(sql)) {
				stm.setString(1, id);
				stm.execute();
				
				transformarResultSetEnReserva(reservas, stm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


	public void Eliminar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE id = ?")){
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(Date fechaEntrada, Date fechaSalida, String valor, String formaPago, Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("UPDATE reservas set fechaEntrada = ?, fechaSalida = ?, valor = ?, formaPago = ? WHERE id = ?")) {
			
			stm.setDate(1, fechaEntrada);
			stm.setDate(2, fechaSalida);
			stm.setString(3, valor);
			stm.setString(4, formaPago);
			stm.setInt(5, id);
			stm.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement stm) throws SQLException {
		try (ResultSet result = stm.getResultSet()) {
			while (result.next()) {
				Reserva reserva = new Reserva(result.getInt(1), result.getDate(2), result.getDate(3), result.getString(4), result.getString(5));
				
				reservas.add(reserva);
			}
			
		}
		
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	