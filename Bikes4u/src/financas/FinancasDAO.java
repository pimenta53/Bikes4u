package financas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

import beans.RegistoTransaccao;


public class FinancasDAO {
  Connection connection = null;
  PreparedStatement ptmt = null;

  public FinancasDAO() {

  }

  private Connection getConnection() throws SQLException {
    Connection conn;
    conn = ConnectionFactory.getInstance().getConnection();
    return conn;
  }
  
  public List<RegistoTransaccao> getAllAluguersDia(){
    ResultSet result = null;
    List<RegistoTransaccao> res = new ArrayList<RegistoTransaccao>();
    try {
      String queryString = "SELECT regA.idRegistoAluguer, regA.data, ra.preco, ra.dataInicio, ra.dataFim, sb.idBicicleta, " +
      		               "sb.designacao, c.nome, sb.nova, cli.nome, fun.nome " +
                           "FROM (((((RegistoAluguers regA INNER JOIN ReservaAluguers ra ON " +
                           "regA.idReservaAluguer = ra.idReservaAluguer) INNER JOIN Clientes cli ON cli.idCliente = ra.idCliente) " +
                           "INNER JOIN Funcionarios fun ON regA.idFuncionario = fun.idFuncionario) INNER JOIN " +
                           "RegistoAluguerBicicletas rab ON regA.idRegistoAluguer = rab.idRegistoAluguer) INNER JOIN " +
                           "StockBicicletas sb ON rab.idBicicleta = sb.idBicicleta) INNER JOIN Categorias c ON " +
                           "c.idCategoria = sb.idCategoria " +
                           "WHERE CURRENT_DATE = DATE(regA.data) " +
                           "ORDER BY regA.idRegistoAluguer ASC ";
      /*
      SELECT regA.idRegistoAluguer, regA.data, ra.preco, ra.dataInicio, ra.dataFim, cli.nome, fun.nome, sb.idBicicleta, sb.designacao, sb.nova, c.nome
      FROM (((((RegistoAluguers regA INNER JOIN ReservaAluguers ra ON regA.idReservaAluguer = ra.idReservaAluguer) INNER JOIN Clientes cli ON 
         cli.idCliente = ra.idCliente) INNER JOIN Funcionarios fun ON regA.idFuncionario = fun.idFuncionario) INNER JOIN RegistoAluguerBicicletas rab ON
         regA.idRegistoAluguer = rab.idRegistoAluguer) INNER JOIN StockBicicletas sb ON rab.idBicicleta = sb.idBicicleta) INNER JOIN Categorias c ON 
         c.idCategoria = sb.idCategoria
      */
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      result = ptmt.executeQuery();
      while(result.next()){
        RegistoTransaccao rt = new RegistoTransaccao();
        rt.setIdTransaccao(result.getLong(1));
        rt.setDataTransaccao(result.getDate(2));
        rt.setPreco(result.getBigDecimal(3));
        rt.setDataInicioAluguer(result.getDate(4));
        rt.setDataFimAluguer(result.getDate(5));
        rt.setIdBicicleta(result.getLong(6));
        rt.setDesignacaoBicicleta(result.getString(7));
        rt.setNomeCategoria(result.getString(8));
        rt.setNova(result.getInt(9));
        rt.setNomeCliente(result.getString(10));
        rt.setNomeFuncionario(result.getString(11));
        res.add(rt);
      }
      System.out.println("The query for Relatorio Diario de Alugueres was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return res;
  }
  
  public List<RegistoTransaccao> getAllVendasDia(){
    ResultSet result = null;
    List<RegistoTransaccao> res = new ArrayList<RegistoTransaccao>();
    try {
      String queryString = "SELECT rv.idRegistoVenda, rv.data, sb.precoVenda, sb.idBicicleta, sb.designacao, c.nome, sb.nova, " +
      		                      "cli.nome, fun.nome " +
                           "FROM (((RegistoVendas rv LEFT JOIN Clientes cli ON rv.idCliente = cli.idCliente) INNER JOIN " +
                           "Funcionarios fun ON rv.idFuncionario = fun.idFuncionario) INNER JOIN StockBicicletas sb ON " +
                           "rv.idBicicleta = sb.idBicicleta) INNER JOIN Categorias c ON sb.idCategoria = c.idCategoria " +
                           "WHERE CURRENT_DATE = DATE(rv.data) " +
                           "ORDER BY rv.idRegistoVenda ASC ";
      
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      result = ptmt.executeQuery();
      while(result.next()){
        RegistoTransaccao rt = new RegistoTransaccao();
        rt.setIdTransaccao(result.getLong(1));
        rt.setDataTransaccao(result.getDate(2));
        rt.setPreco(result.getBigDecimal(3));
        rt.setIdBicicleta(result.getLong(4));
        rt.setDesignacaoBicicleta(result.getString(5));
        rt.setNomeCategoria(result.getString(6));
        rt.setNova(result.getInt(7));
        rt.setNomeCliente(result.getString(8));
        rt.setNomeFuncionario(result.getString(9));
        res.add(rt);
      }
      System.out.println("The query for Relatorio Diario de Vendas was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return res;
  }
  
  public List<RegistoTransaccao> getAllAluguersMesAno(Integer mes, Integer ano){
    ResultSet result = null;
    List<RegistoTransaccao> res = new ArrayList<RegistoTransaccao>();
    try {
      String queryString = "SELECT regA.idRegistoAluguer, regA.data, ra.preco, ra.dataInicio, ra.dataFim, sb.idBicicleta, " +
                           "sb.designacao, c.nome, sb.nova, cli.nome, fun.nome " +
                           "FROM (((((RegistoAluguers regA INNER JOIN ReservaAluguers ra ON " +
                           "regA.idReservaAluguer = ra.idReservaAluguer) INNER JOIN Clientes cli ON cli.idCliente = ra.idCliente) " +
                           "INNER JOIN Funcionarios fun ON regA.idFuncionario = fun.idFuncionario) INNER JOIN " +
                           "RegistoAluguerBicicletas rab ON regA.idRegistoAluguer = rab.idRegistoAluguer) INNER JOIN " +
                           "StockBicicletas sb ON rab.idBicicleta = sb.idBicicleta) INNER JOIN Categorias c ON " +
                           "c.idCategoria = sb.idCategoria " +
                           "WHERE MONTH(regA.data) = ? AND YEAR(regA.data) = ? " +
                           "ORDER BY regA.idRegistoAluguer ASC ";
      
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setInt(1, mes);
      ptmt.setInt(2, ano);
      result = ptmt.executeQuery();
      while(result.next()){
        RegistoTransaccao rt = new RegistoTransaccao();
        rt.setIdTransaccao(result.getLong(1));
        rt.setDataTransaccao(result.getDate(2));
        rt.setPreco(result.getBigDecimal(3));
        rt.setDataInicioAluguer(result.getDate(4));
        rt.setDataFimAluguer(result.getDate(5));
        rt.setIdBicicleta(result.getLong(6));
        rt.setDesignacaoBicicleta(result.getString(7));
        rt.setNomeCategoria(result.getString(8));
        rt.setNova(result.getInt(9));
        rt.setNomeCliente(result.getString(10));
        rt.setNomeFuncionario(result.getString(11));
        res.add(rt);
      }
      System.out.println("The query for Relatorio Mensal de Alugueres was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return res;
  }
  
  public List<RegistoTransaccao> getAllVendasMesAno(Integer mes, Integer ano){
    ResultSet result = null;
    List<RegistoTransaccao> res = new ArrayList<RegistoTransaccao>();
    try {
      String queryString = "SELECT rv.idRegistoVenda, rv.data, sb.precoVenda, sb.idBicicleta, sb.designacao, c.nome, sb.nova, " +
                                  "cli.nome, fun.nome " +
                           "FROM (((RegistoVendas rv LEFT JOIN Clientes cli ON rv.idCliente = cli.idCliente) INNER JOIN " +
                           "Funcionarios fun ON rv.idFuncionario = fun.idFuncionario) INNER JOIN StockBicicletas sb ON " +
                           "rv.idBicicleta = sb.idBicicleta) INNER JOIN Categorias c ON sb.idCategoria = c.idCategoria " +
                           "WHERE MONTH(rv.data) = ? AND YEAR(rv.data) = ? " +
                           "ORDER BY rv.idRegistoVenda ASC ";
      
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setInt(1, mes);
      ptmt.setInt(2, ano);
      result = ptmt.executeQuery();
      while(result.next()){
        RegistoTransaccao rt = new RegistoTransaccao();
        rt.setIdTransaccao(result.getLong(1));
        rt.setDataTransaccao(result.getDate(2));
        rt.setPreco(result.getBigDecimal(3));
        rt.setIdBicicleta(result.getLong(4));
        rt.setDesignacaoBicicleta(result.getString(5));
        rt.setNomeCategoria(result.getString(6));
        rt.setNova(result.getInt(7));
        rt.setNomeCliente(result.getString(8));
        rt.setNomeFuncionario(result.getString(9));
        res.add(rt);
      }
      System.out.println("The query for Relatorio Mensal de Vendas was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return res;
  }
}
