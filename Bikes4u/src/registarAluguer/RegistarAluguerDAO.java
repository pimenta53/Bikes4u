package registarAluguer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Bicicleta;
import bikes.categorias.CategoriaB;
import reservas.ReservaAluguer;
import util.ConnectionFactory;

public class RegistarAluguerDAO {

	private Connection connection = null;
	private PreparedStatement ptmt = null;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public List<ReservaAluguer> getReservasAluguer() {
		ResultSet result = null;
		List<ReservaAluguer> listaReservasAluguer = new ArrayList<ReservaAluguer>();
		try {
			String queryString = "SELECT ra.idReservaAluguer, ra.idCliente, ra.dataInicio, ra.dataFim, rc.quantidade,"
					+ "rc.idCategoria, ra.preco, c.nome FROM ( ReservaAluguers ra LEFT JOIN ReservaCategorias "
					+ "rc ON rc.idReservaAluguer = ra.idReservaAluguer LEFT JOIN Categorias c ON "
					+ "c.idCategoria = rc.idCategoria) WHERE ra.idReservaAluguer NOT IN "
					+ "(SELECT reg.idReservaAluguer FROM RegistoAluguers reg) ORDER BY dataInicio ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				String idReservaAluguer = result.getString(1);
				String idCliente = result.getString(2);
				Date dataInicio = result.getDate(3);
				Date dataFim = result.getDate(4);
				int quantidade = result.getInt(5);
				String idCategoria = result.getString(6);
				BigDecimal preco = result.getBigDecimal(7);
				String nomeCategoria = result.getString(8);
				CategoriaB cat = new CategoriaB();
				cat.setId(idCategoria);
				cat.setTipo(nomeCategoria);

                ReservaAluguer ra = new ReservaAluguer();
                ra.setIdReservaAluguer(idReservaAluguer);
                ra.setDataInicio(dataInicio);
                ra.setDataFim(dataFim);
                ra.setQuantidade(quantidade);
                ra.setTotal(preco);
                ra.setIdCliente(idCliente);
                ra.setCat(cat);

				listaReservasAluguer.add(ra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null)
				try {
					ptmt.close();
				} catch (SQLException logOrIgnore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException logOrIgnore) {
				}
		}
		return listaReservasAluguer;
	}

	public List<ReservaAluguer> getReservasAluguerPesquisar(String pesq) {
		ResultSet result = null;
		List<ReservaAluguer> listaReservasAluguer = new ArrayList<ReservaAluguer>();
		try {
			String queryString = "SELECT ra.idReservaAluguer, ra.idCliente, ra.dataInicio, ra.dataFim, rc.quantidade, "
					+ "rc.idCategoria, ra.preco, c.nome FROM ( ReservaAluguers ra LEFT JOIN ReservaCategorias rc "
					+ "ON rc.idReservaAluguer = ra.idReservaAluguer LEFT JOIN Categorias c ON "
					+ "c.idCategoria = rc.idCategoria) WHERE ra.idReservaAluguer NOT IN "
					+ "(SELECT reg.idReservaAluguer FROM RegistoAluguers reg) AND (ra.idReservaAluguer LIKE ? "
					+ "OR ra.idCliente LIKE ? OR ra.dataInicio LIKE ?) ORDER BY ra.dataInicio ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			ptmt.setString(1, like(pesq));
			ptmt.setString(2, like(pesq));
			ptmt.setString(3, like(pesq));
			result = ptmt.executeQuery();
			while (result.next()) {
				String idReservaAluguer = result.getString(1);
				String idCliente = result.getString(2);
				Date dataInicio = result.getDate(3);
				Date dataFim = result.getDate(4);
				int quantidade = result.getInt(5);
				String idCategoria = result.getString(6);
				BigDecimal preco = result.getBigDecimal(7);
				String nomeCategoria = result.getString(8);
                CategoriaB cat = new CategoriaB();
                cat.setId(idCategoria);
                cat.setTipo(nomeCategoria);
                ReservaAluguer ra = new ReservaAluguer();
                ra.setIdReservaAluguer(idReservaAluguer);
                ra.setDataInicio(dataInicio);
                ra.setDataFim(dataFim);
                ra.setQuantidade(quantidade);
                ra.setTotal(preco);
                ra.setIdCliente(idCliente);
                ra.setCat(cat);
					
				listaReservasAluguer.add(ra);
			}
			System.out
					.println("The query to ListaReservas was executed successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null)
				try {
					ptmt.close();
				} catch (SQLException logOrIgnore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException logOrIgnore) {
				}
		}
		return listaReservasAluguer;
	}

	/** Surround by '%', for LIKE operator. */
	private String like(String aSearchTerm) {
		return "%".concat(aSearchTerm).concat("%");
	}

	public List<Bicicleta> getBicicletasReserva(String idCategoria,
			int quantidade) {
		ResultSet result = null;
		List<Bicicleta> bic = new ArrayList<Bicicleta>();
		try {
			String queryString = "SELECT sb.idBicicleta, sb.designacao, c.precoAluguer FROM (StockBicicletas sb "
					+ "LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria) "
					+ "LEFT JOIN Estados e ON sb.idEstado = e.idEstado "
					+ "WHERE e.idEstado = 4 AND sb.idCategoria=?"
					+ "ORDER BY sb.idBicicleta ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			ptmt.setString(1, idCategoria);
			result = ptmt.executeQuery();
			int i = 0;
			while (result.next() && i != quantidade) {
				System.out.println("AQUi");
				Bicicleta bc = new Bicicleta();
				bc.setIdBicicleta(result.getLong(1));
				bc.setDesignacao(result.getString(2));
				bc.setPrecoAluguer(result.getBigDecimal(3));
				bic.add(bc);
				i++;
			}
			System.out
					.println("The query to StockBicicletas was executed successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null)
				try {
					ptmt.close();
				} catch (SQLException logOrIgnore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException logOrIgnore) {
				}
		}
		return bic;
	}

	public String registarReservaAluguer(RegistoAluguer registoAluguer) {
		int success = 0;
		String idRegisto = null;
		ResultSet result = null;
		try {
			String queryString = "INSERT INTO RegistoAluguers(idFuncionario, idReservaAluguer)  VALUES(?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, registoAluguer.getIdFuncionario());
			ptmt.setString(2, registoAluguer.getIdReservaAluguer());
			success = ptmt.executeUpdate();
			if (success != 0) {
				queryString = "SELECT idRegistoAluguer FROM RegistoAluguers WHERE idFuncionario=? AND idReservaAluguer=?";
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, registoAluguer.getIdFuncionario());
				ptmt.setString(2, registoAluguer.getIdReservaAluguer());
				result = ptmt.executeQuery();
				while (result.next()) {
					idRegisto = result.getString(1);
				}
			}
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
		return idRegisto;
	}

	public int registarAluguerBicicletas(String idRegistoAluguer,
			Long idBicicleta) {
		int success = 0;
		try {
			String queryString = "INSERT INTO RegistoAluguerBicicletas(idRegistoAluguer, idBicicleta)  VALUES(?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, idRegistoAluguer);
			ptmt.setLong(2, idBicicleta);
			success = ptmt.executeUpdate();
			if (success != 0) {
				queryString = "UPDATE StockBicicletas SET idEstado=1 WHERE idBicicleta=?";
				ptmt = connection.prepareStatement(queryString);
				ptmt.setLong(1, idBicicleta);
				success = ptmt.executeUpdate();
			}

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
		return success;
	}

	public List<ReservaAluguer> getReservasAluguerAConcluir() {
		ResultSet result = null;
		List<ReservaAluguer> listaReservasAluguer = new ArrayList<ReservaAluguer>();
		try {
			String queryString = "SELECT ra.idReservaAluguer, ra.idCliente, ra.dataInicio, ra.dataFim, rc.quantidade,"
					+ " rc.idCategoria, ra.preco, c.nome FROM ( ReservaAluguers ra LEFT JOIN ReservaCategorias rc ON"
					+ " rc.idReservaAluguer = ra.idReservaAluguer LEFT JOIN Categorias c ON c.idCategoria = rc.idCategoria"
					+ " LEFT JOIN RegistoAluguers reg ON reg.idReservaAluguer =ra.idReservaAluguer ) WHERE reg.concluido=0"
					+ " AND TO_DAYS(NOW()) >= TO_DAYS(ra.dataFim) ORDER BY dataFim ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				String idReservaAluguer = result.getString(1);
				String idCliente = result.getString(2);
				Date dataInicio = result.getDate(3);
				Date dataFim = result.getDate(4);
				int quantidade = result.getInt(5);
				String idCategoria = result.getString(6);
				BigDecimal preco = result.getBigDecimal(7);
				String nomeCategoria = result.getString(8);

                CategoriaB cat = new CategoriaB();
                cat.setId(idCategoria);
                cat.setTipo(nomeCategoria);
                
                ReservaAluguer ra = new ReservaAluguer();
                ra.setIdReservaAluguer(idReservaAluguer);
                ra.setDataInicio(dataInicio);
                ra.setDataFim(dataFim);
                ra.setQuantidade(quantidade);
                ra.setTotal(preco);
                ra.setIdCliente(idCliente);
                ra.setCat(cat);

				listaReservasAluguer.add(ra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null)
				try {
					ptmt.close();
				} catch (SQLException logOrIgnore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException logOrIgnore) {
				}
		}
		return listaReservasAluguer;
	}

	public int terminarRegisto(String idReservaAluguer) {
		int success = 0;
		try {
			String queryString = "UPDATE RegistoAluguers SET concluido=1 WHERE idReservaAluguer=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, idReservaAluguer);
			success = ptmt.executeUpdate();
			if (success != 0) {
				queryString = "UPDATE StockBicicletas sb SET sb.idEstado=4 WHERE sb.idBicicleta IN "
						+ "(select sb.idBicicleta from RegistoAluguerBicicletas rab, RegistoAluguers ra "
						+ "WHERE rab.idRegistoAluguer = ra.idRegistoAluguer AND  ra.idReservaAluguer=?)";
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, idReservaAluguer);
				success = ptmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null)
				try {
					ptmt.close();
				} catch (SQLException logOrIgnore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException logOrIgnore) {
				}
		}
		return success;
	}

	public List<ReservaAluguer> getReservasAConcluirPesquisar(String pesq) {
		ResultSet result = null;
		List<ReservaAluguer> listaReservasAluguer = new ArrayList<ReservaAluguer>();
		try {
			String queryString = "SELECT ra.idReservaAluguer, ra.idCliente, ra.dataInicio, ra.dataFim, rc.quantidade,"
					+ " rc.idCategoria, ra.preco, c.nome FROM ( ReservaAluguers ra LEFT JOIN ReservaCategorias rc ON"
					+ " rc.idReservaAluguer = ra.idReservaAluguer LEFT JOIN Categorias c ON c.idCategoria = rc.idCategoria"
					+ " LEFT JOIN RegistoAluguers reg ON reg.idReservaAluguer =ra.idReservaAluguer ) WHERE reg.concluido=0"
					+ " AND TO_DAYS(NOW()) >= TO_DAYS(ra.dataFim) AND (ra.idReservaAluguer LIKE ?"
					+ " OR ra.idCliente LIKE ? OR ra.dataInicio LIKE ?) ORDER BY dataFim ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			ptmt.setString(1, like(pesq));
			ptmt.setString(2, like(pesq));
			ptmt.setString(3, like(pesq));
			result = ptmt.executeQuery();
			while (result.next()) {
				String idReservaAluguer = result.getString(1);
				String idCliente = result.getString(2);
				Date dataInicio = result.getDate(3);
				Date dataFim = result.getDate(4);
				int quantidade = result.getInt(5);
				String idCategoria = result.getString(6);
				BigDecimal preco = result.getBigDecimal(7);
				String nomeCategoria = result.getString(8);

                CategoriaB cat = new CategoriaB();
                cat.setId(idCategoria);
                cat.setTipo(nomeCategoria);
                
                ReservaAluguer ra = new ReservaAluguer();
                ra.setIdReservaAluguer(idReservaAluguer);
                ra.setDataInicio(dataInicio);
                ra.setDataFim(dataFim);
                ra.setQuantidade(quantidade);
                ra.setTotal(preco);
                ra.setIdCliente(idCliente);
                ra.setCat(cat);

				listaReservasAluguer.add(ra);
			}
			System.out
					.println("The query to ListaReservas was executed successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null)
				try {
					ptmt.close();
				} catch (SQLException logOrIgnore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException logOrIgnore) {
				}
		}
		return listaReservasAluguer;
	}

	public List<ReservaAluguer> getReservasAluguerPorConcluir() {
		ResultSet result = null;
		List<ReservaAluguer> listaReservasPorConcluir = new ArrayList<ReservaAluguer>();
		try {
			String queryString = "SELECT ra.idReservaAluguer, ra.idCliente, ra.dataInicio, ra.dataFim, rc.quantidade,"
					+ " rc.idCategoria, ra.preco, c.nome FROM ( ReservaAluguers ra LEFT JOIN ReservaCategorias rc ON"
					+ " rc.idReservaAluguer = ra.idReservaAluguer LEFT JOIN Categorias c ON c.idCategoria = rc.idCategoria"
					+ " LEFT JOIN RegistoAluguers reg ON reg.idReservaAluguer =ra.idReservaAluguer ) WHERE reg.concluido=0"
					+ " ORDER BY dataFim ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				String idReservaAluguer = result.getString(1);
				String idCliente = result.getString(2);
				Date dataInicio = result.getDate(3);
				Date dataFim = result.getDate(4);
				int quantidade = result.getInt(5);
				String idCategoria = result.getString(6);
				BigDecimal preco = result.getBigDecimal(7);
				String nomeCategoria = result.getString(8);

                CategoriaB cat = new CategoriaB();
                cat.setId(idCategoria);
                cat.setTipo(nomeCategoria);

                ReservaAluguer ra = new ReservaAluguer();
                ra.setIdReservaAluguer(idReservaAluguer);
                ra.setDataInicio(dataInicio);
                ra.setDataFim(dataFim);
                ra.setQuantidade(quantidade);
                ra.setTotal(preco);
                ra.setIdCliente(idCliente);
                ra.setCat(cat);

				listaReservasPorConcluir.add(ra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null)
				try {
					ptmt.close();
				} catch (SQLException logOrIgnore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException logOrIgnore) {
				}
		}
		return listaReservasPorConcluir;
	}
}
