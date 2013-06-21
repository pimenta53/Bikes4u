package vendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

import beans.Bicicleta;
import beans.Cliente;
import beans.Venda;

public class VendasDAO {
  Connection connection = null;
  PreparedStatement ptmt = null;

  public VendasDAO() {

  }

  private Connection getConnection() throws SQLException {
    Connection conn;
    conn = ConnectionFactory.getInstance().getConnection();
    return conn;
  }
  
  public List<Bicicleta> getAllBicicletasArmazem(){
    ResultSet result;
    List<Bicicleta> lstBic = new ArrayList<Bicicleta>();
    try {
      String queryString = "SELECT * FROM (SELECT sb.idBicicleta, sb.designacao, sb.nova, c.nome, man.dataFim " +
      		                "FROM (StockBicicletas sb LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria) LEFT JOIN " +
      		                "RegistoManutencaos man ON sb.idBicicleta = man.idBicicleta " +
      		                "WHERE sb.idEstado = 4 " +
      		                "ORDER BY man.dataFim Desc) AS datas " +
      		                "GROUP BY idBicicleta";
      
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      result = ptmt.executeQuery();
      while(result.next()){
        Bicicleta b = new Bicicleta();
        b.setIdBicicleta(result.getLong(1));
        b.setDesignacao(result.getString(2));
        b.setNova(result.getInt(3));
        b.setNomeCategoria(result.getString(4));
        b.setUltimaManutencao(result.getDate(5));
        lstBic.add(b);
      }
      System.out.println("The query for Bicicletas no Armazem was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    
    return lstBic;
  }

  public void getDadosVenda(Venda v){
    ResultSet result;
    
    try {
      String queryString = "SELECT sb.designacao, sb.precoVenda, sb.nova, c.nome " +
      		               "FROM StockBicicletas sb LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria " +
      		               "WHERE sb.idBicicleta = ?";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setLong(1, v.getIdBicicleta());
      result = ptmt.executeQuery();
      while(result.next()){
        v.setDesignacaoBicicleta(result.getString(1));
        v.setPreco(result.getBigDecimal(2));
        v.setNova(result.getInt(3));
        v.setNomeCategoria(result.getString(4));
      }
      System.out.println("The query for Dados de Venda was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
  }

  public List<Cliente> getAllClientes(){
    ResultSet result;
    List<Cliente> lstCli = new ArrayList<Cliente>();
    try{
      String queryString = "SELECT cli.idCliente, cli.nomeCliente FROM Clientes Cli";
      
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      result = ptmt.executeQuery();
      while(result.next()){
        Cliente c = new Cliente();
        c.setIdCliente(result.getString(1));
        c.setNomeCliente(result.getString(2));
        lstCli.add(c);
      }
      System.out.println("The query to get all Clientes was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    
    return lstCli;
  }
  
  public boolean addVenda(String idBicicleta, String idCliente, String idFuncionario){
    int valid = 0;
    
    try {
      String queryString = "";
      if(idCliente != null) queryString = "INSERT INTO RegistoVendas (idBicicleta, idFuncionario, idCliente) VALUES(?,?,?)";
      else queryString = "INSERT INTO RegistoVendas (idBicicleta, idFuncionario) VALUES(?,?)";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, idBicicleta);
      ptmt.setString(2, idFuncionario);
      if(idCliente != null) ptmt.setString(3, idCliente);
      valid = ptmt.executeUpdate();
      System.out.println("Registo de Venda Added Successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    if (valid != 0) return true;
    else return false;
  }
  
  public void setEstado(String idBicicleta){
    
    try {
      String queryString = "UPDATE StockBicicletas SET idEstado = 2 WHERE idBicicleta = ?";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, idBicicleta);
      ptmt.executeUpdate();
      System.out.println("Estado da Bicicleta " + idBicicleta +" updated Successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    
  }
}
