package manutencao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Bicicleta;
import util.ConnectionFactory;

public class ManutencaoDAO {
	private Connection connection = null;
	private PreparedStatement ptmt = null;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public int add(Manutencao manutencao) {
		int success = 0;
		try {
			String queryString = "INSERT INTO RegistoManutencaos(descricaoAvaria, dataFim, precoPosManutencao, idBicicleta, idFuncionario)  VALUES(?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, manutencao.getDescricaoAvaria());
			ptmt.setDate(2, manutencao.getDataFim());
			ptmt.setBigDecimal(3, manutencao.getPrecoPosManutencao());
			ptmt.setString(4, manutencao.getIdBicicleta());
			ptmt.setString(5, manutencao.getIdFuncionario());
			success = ptmt.executeUpdate();
			System.out.println("Data Added Successfully");
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

	public int deleteManutencao(long idManutencao) {
		int success = 0;
		try {
			String queryString = "DELETE FROM RegistoManutencaos WHERE idManutencao=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, idManutencao);
			success = ptmt.executeUpdate();
			if (success != 0) {
				System.out.println("Manutencao deleted Successfully");
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

	public List<Manutencoes> getAllManutencoes() {
		ResultSet result = null;
		List<Manutencoes> manutencoes = new ArrayList<Manutencoes>();
		try {
			String queryString = "SELECT sb.idBicicleta, sb.designacao, c.nome, rm.descricaoAvaria, rm.dataInicio, rm.dataFim, rm.precoPosManutencao, f.nomeFuncionario, rm.idManutencao, rm.terminado FROM (RegistoManutencaos rm LEFT JOIN StockBicicletas sb ON rm.idBicicleta = sb.idBicicleta LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria LEFT JOIN Funcionarios f ON rm.idFuncionario = f.idFuncionario LEFT JOIN Estados e ON e.idEstado = sb.idEstado) WHERE e.idEstado=3 AND rm.terminado=0 ORDER BY rm.dataInicio ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				Manutencoes m = new Manutencoes();
				m.setIdBicicleta(result.getLong(1));
				m.setDesignacao(result.getString(2));
				m.setNomeCategoria(result.getString(3));
				m.setDescricaoAvaria(result.getString(4));
				m.setDataInicio(result.getDate(5));
				m.setDataFim(result.getDate(6));
				m.setPrecoPosManutencao(result.getBigDecimal(7));
				m.setNomeFuncionario(result.getString(8));
				m.setIdManutencao(result.getLong(9));
				m.setTerminado(result.getInt(10));
				manutencoes.add(m);
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
		return manutencoes;
	}

	public List<Manutencoes> getAllBicicletasManutencao() {
		ResultSet result = null;
		List<Manutencoes> manutencoes = new ArrayList<Manutencoes>();
		try {
			String queryString = "SELECT sb.idBicicleta, sb.designacao, c.nome, rm.descricaoAvaria, "
					+ "rm.dataInicio, rm.dataFim, rm.precoPosManutencao, f.nomeFuncionario, rm.idManutencao,"
					+ " rm.terminado FROM (RegistoManutencaos rm LEFT JOIN StockBicicletas sb ON "
					+ "rm.idBicicleta = sb.idBicicleta LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria"
					+ " LEFT JOIN Funcionarios f ON rm.idFuncionario = f.idFuncionario LEFT JOIN "
					+ "Estados e ON e.idEstado = sb.idEstado) WHERE rm.terminado=1 ORDER BY rm.idManutencao";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				Manutencoes m = new Manutencoes();
				m.setIdBicicleta(result.getLong(1));
				m.setDesignacao(result.getString(2));
				m.setNomeCategoria(result.getString(3));
				m.setDescricaoAvaria(result.getString(4));
				m.setDataInicio(result.getDate(5));
				m.setDataFim(result.getDate(6));
				m.setPrecoPosManutencao(result.getBigDecimal(7));
				m.setNomeFuncionario(result.getString(8));
				m.setIdManutencao(result.getLong(9));
				m.setTerminado(result.getInt(10));
				manutencoes.add(m);
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
		return manutencoes;
	}

	public List<Manutencoes> getAllManutencoesIniciar() {
		ResultSet result = null;
		List<Manutencoes> manutencoes = new ArrayList<Manutencoes>();
		try {
			String queryString = "SELECT sb.idBicicleta, sb.designacao, c.nome, rm.descricaoAvaria, rm.dataInicio, rm.dataFim, rm.precoPosManutencao, f.nomeFuncionario, rm.idManutencao, rm.terminado FROM (RegistoManutencaos rm LEFT JOIN StockBicicletas sb ON rm.idBicicleta = sb.idBicicleta LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria LEFT JOIN Funcionarios f ON rm.idFuncionario = f.idFuncionario LEFT JOIN Estados e ON e.idEstado = sb.idEstado) WHERE TO_DAYS(NOW()) - TO_DAYS(rm.dataInicio) >=0 AND TO_DAYS(NOW()) - TO_DAYS(rm.dataFim) <=0 AND e.idEstado!=3 AND rm.terminado=0 ORDER BY rm.dataInicio ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				Manutencoes m = new Manutencoes();
				m.setIdBicicleta(result.getLong(1));
				m.setDesignacao(result.getString(2));
				m.setNomeCategoria(result.getString(3));
				m.setDescricaoAvaria(result.getString(4));
				m.setDataInicio(result.getDate(5));
				m.setDataFim(result.getDate(6));
				m.setPrecoPosManutencao(result.getBigDecimal(7));
				m.setNomeFuncionario(result.getString(8));
				m.setIdManutencao(result.getLong(9));
				m.setTerminado(result.getInt(10));
				manutencoes.add(m);
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
		return manutencoes;
	}

	public List<Manutencoes> getAllManutencoesFim() {
		ResultSet result = null;
		List<Manutencoes> manutencoes = new ArrayList<Manutencoes>();
		try {
			String queryString = "SELECT sb.idBicicleta, sb.designacao, c.nome, rm.descricaoAvaria, rm.dataInicio, rm.dataFim, rm.precoPosManutencao, f.nomeFuncionario, rm.idManutencao, rm.terminado FROM (RegistoManutencaos rm LEFT JOIN StockBicicletas sb ON rm.idBicicleta = sb.idBicicleta LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria LEFT JOIN Funcionarios f ON rm.idFuncionario = f.idFuncionario LEFT JOIN Estados e ON e.idEstado = sb.idEstado) WHERE TO_DAYS(NOW()) - TO_DAYS(rm.dataFim) >=0 AND e.idEstado=3 AND rm.terminado = 0 ORDER BY rm.dataFim ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				Manutencoes m = new Manutencoes();
				m.setIdBicicleta(result.getLong(1));
				m.setDesignacao(result.getString(2));
				m.setNomeCategoria(result.getString(3));
				m.setDescricaoAvaria(result.getString(4));
				m.setDataInicio(result.getDate(5));
				m.setDataFim(result.getDate(6));
				m.setPrecoPosManutencao(result.getBigDecimal(7));
				m.setNomeFuncionario(result.getString(8));
				m.setIdManutencao(result.getLong(9));
				m.setTerminado(result.getInt(10));
				manutencoes.add(m);
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
		return manutencoes;
	}

	public List<Bicicleta> getAllBicicletas() {
		ResultSet result = null;
		List<Bicicleta> bic = new ArrayList<Bicicleta>();
		try {
			String queryString = "SELECT sb.idBicicleta, sb.designacao, sb.nova, sb.precoVenda, c.nome, c.precoAluguer, e.nome "
					+ "FROM (StockBicicletas sb LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria) LEFT JOIN "
					+ "Estados e ON sb.idEstado = e.idEstado "
					+ "WHERE e.idEstado = 1 OR e.idEstado = 4 "
					+ "ORDER BY sb.designacao ASC";
			connection = getConnection();
			ptmt = (PreparedStatement) connection.prepareStatement(queryString);
			result = ptmt.executeQuery();
			while (result.next()) {
				Bicicleta bc = new Bicicleta();
				bc.setIdBicicleta(result.getLong(1));
				bc.setDesignacao(result.getString(2));
				bc.setNova(result.getInt(3));
				bc.setPrecoVenda(result.getBigDecimal(4));
				bc.setNomeCategoria(result.getString(5));
				bc.setPrecoAluguer(result.getBigDecimal(6));
				bc.setNomeEstado(result.getString(7));
				bic.add(bc);
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

	public int retirarBicicletaManutencao(long idBicicleta, long idManutencao,
			BigDecimal precoPosManutencao) {
		int success = 0;
		try {
			System.out.println(precoPosManutencao);
			String queryString = "UPDATE StockBicicletas SET idEstado=4, precoVenda=? WHERE idBicicleta=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setBigDecimal(1, precoPosManutencao);
			ptmt.setLong(2, idBicicleta);
			success = ptmt.executeUpdate();

			queryString = "UPDATE RegistoManutencaos SET terminado=1 WHERE idManutencao=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, idManutencao);
			success = ptmt.executeUpdate();

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

	public int adicionarBicicletaManutencao(String idBicicleta) {
		int success = 0;
		try {
			String queryString = "UPDATE StockBicicletas SET idEstado=3 WHERE idBicicleta=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, idBicicleta);
			ptmt.executeUpdate();
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

	public int editar(Manutencao manutencao) {
		int success = 0;
		try {
			String queryString = "UPDATE RegistoManutencaos SET descricaoAvaria=?, dataFim=?, precoPosManutencao=?, idBicicleta=?, idFuncionario=? WHERE idManutencao=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, manutencao.getDescricaoAvaria());
			ptmt.setDate(2, manutencao.getDataFim());
			ptmt.setBigDecimal(3, manutencao.getPrecoPosManutencao());
			ptmt.setString(4, manutencao.getIdBicicleta());
			ptmt.setString(5, manutencao.getIdFuncionario());
			ptmt.setString(6, manutencao.getIdManutencao());

			success = ptmt.executeUpdate();
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

	public int atualizarEstado(Long idBicicleta) {
		int success = 0;
		try {
			String queryString = "UPDATE StockBicicletas SET idEstado=4 WHERE idBicicleta=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, idBicicleta);
			success = ptmt.executeUpdate();
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
}
