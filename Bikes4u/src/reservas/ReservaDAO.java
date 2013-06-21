package reservas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import util.ConnectionFactory;


public class ReservaDAO {

	private Connection connection = null;
	private PreparedStatement ptmt = null;

	public ReservaDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public void addRes(ReservaAluguer reservaAluguer) {
		  ResultSet generatedKeys = null;
		  String idReserva ;
		  
		try {
			String queryString = "INSERT INTO reservaaluguers(dataInicio, dataFim, preco, idCliente)  VALUES(?,?,?,?)";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
			ptmt.setDate(1, reservaAluguer.getDataInicio());
			ptmt.setDate(2, reservaAluguer.getDataFim());
			ptmt.setBigDecimal(3, reservaAluguer.getTotal());
			ptmt.setString(4, reservaAluguer.getIdCliente());
			ptmt.executeUpdate();
			generatedKeys = ptmt.getGeneratedKeys();
		     if (generatedKeys.next()) idReserva = generatedKeys.getString(1);
		     else throw new SQLException("Creating Cliente failed, no generated key obtained.");
		     System.out.println("REserva Added Successfully with the id " + idReserva + "!");
		     reservaAluguer.setIdReservaAluguer(idReserva);
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addResCat(ReservaAluguer reservaAluguer) {
		  ResultSet generatedKeys = null;
		  String idReserva ;
		  
		try {
			String queryString = "INSERT INTO ReservaCategorias(idReservaAluguer,idCategoria,quantidade) VALUES(?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, reservaAluguer.getIdReservaAluguer());
			ptmt.setString(2, reservaAluguer.getCat().getId());
			ptmt.setInt(3, reservaAluguer.getQuantidade());
			ptmt.executeUpdate();
		    System.out.println("REserva adicionada em condições");
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
