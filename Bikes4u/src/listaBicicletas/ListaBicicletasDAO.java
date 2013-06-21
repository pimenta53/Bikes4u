package listaBicicletas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

import beans.Bicicleta;



public class ListaBicicletasDAO {
  Connection connection = null;
  PreparedStatement ptmt = null;

  public ListaBicicletasDAO() {

  }

  private Connection getConnection() throws SQLException {
    Connection conn;
    conn = ConnectionFactory.getInstance().getConnection();
    return conn;
  }
  
  public List<Bicicleta> getAllBicicletas(){
    ResultSet result = null;
    List<Bicicleta> bic = new ArrayList<Bicicleta>();
    try {
      String queryString = "SELECT sb.idBicicleta, sb.designacao, sb.nova, sb.precoVenda, sb.imagem, c.nome, c.precoAluguer, e.nome, e.idEstado " +
      		               "FROM (StockBicicletas sb LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria) LEFT JOIN " +
      		               "Estados e ON sb.idEstado = e.idEstado " +
      		               "WHERE e.idEstado != 2 " +
      		               "ORDER BY sb.designacao ASC";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      result = ptmt.executeQuery();
      while(result.next()){
        Bicicleta bc = new Bicicleta();
        bc.setIdBicicleta(result.getLong(1));
        bc.setDesignacao(result.getString(2));
        bc.setNova(result.getInt(3));
        bc.setPrecoVenda(result.getBigDecimal(4));
        bc.setImagem(result.getString(5));
        bc.setNomeCategoria(result.getString(6));
        bc.setPrecoAluguer(result.getBigDecimal(7));
        bc.setNomeEstado(result.getString(8));
        bc.setIdEstado(result.getLong(9));
        bic.add(bc);
      }
      System.out.println("The query to StockBicicletas was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return bic;
  }
  
  public List<Bicicleta> getAllBicicletasPesquisa(String pesq){
    ResultSet result = null;
    List<Bicicleta> bic = new ArrayList<Bicicleta>();
    try {
      String queryString = "SELECT sb.idBicicleta, sb.designacao, sb.nova, sb.precoVenda, sb.imagem, c.nome, c.precoAluguer, e.nome, e.idEstado " +
                           "FROM (StockBicicletas sb LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria) LEFT JOIN " +
                           "Estados e ON sb.idEstado = e.idEstado " +
                           "WHERE e.idEstado != 2 AND (sb.designacao LIKE ? OR c.nome LIKE ? OR e.nome LIKE ?) " +
                           "ORDER BY sb.designacao ASC";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, like(pesq));
      ptmt.setString(2, like(pesq));
      ptmt.setString(3, like(pesq));
      result = ptmt.executeQuery();
      while(result.next()){
        Bicicleta bc = new Bicicleta();
        bc.setIdBicicleta(result.getLong(1));
        bc.setDesignacao(result.getString(2));
        bc.setNova(result.getInt(3));
        bc.setPrecoVenda(result.getBigDecimal(4));
        bc.setImagem(result.getString(5));
        bc.setNomeCategoria(result.getString(6));
        bc.setPrecoAluguer(result.getBigDecimal(7));
        bc.setNomeEstado(result.getString(8));
        bc.setIdEstado(result.getLong(9));
        bic.add(bc);
      }
      System.out.println("The query to StockBicicletas was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return bic;
  }
  
  /** Surround by '%', for LIKE operator. */
  private String like(String aSearchTerm){
    return "%".concat(aSearchTerm).concat("%"); 
  }
}
