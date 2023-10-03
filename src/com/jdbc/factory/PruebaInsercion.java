package com.jdbc.factory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaInsercion {

	public static void main(String[] args) throws SQLException {
		 
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.createConnection();
		
		//1 Statement o PReparedStatement
		//2 execute()
		//3 ResultSet
		
		PreparedStatement prueba = connection.prepareStatement("INSERT INTO RESERVAS (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		
		//conversion DATE YYYY-MM-DD
		//set de los datos
		
		Date fechaEntrada = Date.valueOf("2023-09-29");	
		Date fechaSalida = Date.valueOf("2023-09-30");
		
		prueba.setDate(1, fechaEntrada);
		prueba.setDate(2, fechaSalida);
		prueba.setString(3, "1400");
		prueba.setString(4, "efectivo");
		
		prueba.execute();
		
		ResultSet result = prueba.getGeneratedKeys();
		
		while (result.next()) {
			Integer id = result.getInt(1);
			System.out.println("el id creado fue: " + id);
		}
		
		connection.close();
		System.out.println("database closed");
	}

}