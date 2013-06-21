package acesso;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.PasswordHasher;

import beans.Bicicleta;
import beans.Cliente;
import beans.Login;
import beans.Reserva;


public class AcessoDAO {
  Connection connection = null;
  PreparedStatement ptmt = null;

  public AcessoDAO() {
  }

  private Connection getConnection() throws SQLException {
    Connection conn;
    conn = ConnectionFactory.getInstance().getConnection();
    return conn;
  }
  
  public boolean checkCliente(Login loginBean) {
    ResultSet result = null;
    boolean correcto = false;
    try {
      String queryString = "SELECT * FROM Clientes WHERE email = ?";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, loginBean.getEmail());
      result = ptmt.executeQuery();
      while (result.next()) { // process results one row at a time
        correcto = PasswordHasher.hash(loginBean.getPassword()).equals(result.getString(3));
        if (correcto){
          loginBean.setId(result.getString(1));
          loginBean.setNomeLogin(result.getString(2));
          loginBean.setNome(result.getString(4));
          //loginBean.setEmail(email);
          loginBean.setCartaoCredito(result.getString(6));
          break;
        }
      }
      System.out.println("The query to Clientes was executed successfully!");
    } 
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return correcto;
  }
  
  public boolean checkFuncionario(Login loginBean) {
    ResultSet result = null;
    boolean correcto = false;
    try {
      String queryString = "SELECT * FROM Funcionarios WHERE email = ?";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, loginBean.getEmail());
      result = ptmt.executeQuery();
      while (result.next()) { // process results one row at a time
        correcto = PasswordHasher.hash(loginBean.getPassword()).equals(result.getString(3));
        if (correcto){
          loginBean.setId(result.getString(1));
          loginBean.setNomeLogin(result.getString(2));
          loginBean.setNome(result.getString(4));
          //loginBean.setEmail(email);
          break;
        }
      }
      System.out.println("The query to Funcionarios was executed successfully!");
    } 
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return correcto;
  }

  public List<Reserva> getReservas(String id){
    ResultSet result = null;
    List<Reserva> res = new ArrayList<Reserva>();
    try {
      String queryString = "SELECT ra.idReservaAluguer, ra.dataInicio, ra.dataFim, ra.preco " +
                           "FROM ReservaAluguers ra INNER JOIN RegistoAluguers regA ON " +
                           "regA.idReservaAluguer = ra.idReservaAluguer " +
                           "WHERE ra.dataInicio > CURRENT_DATE AND ra.dataInicio <= CURRENT_DATE + 7 AND ra.idCliente = ? " +
                           "ORDER BY ra.dataInicio ASC " +
                           "LIMIT 5";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, id);
      result = ptmt.executeQuery();
      while(result.next()){
        Reserva r = new Reserva();
        r.setIdReserva(result.getLong(1));
        r.setDataInicio(result.getDate(2));
        r.setDataFim(result.getDate(3));
        r.setPreco(result.getBigDecimal(4));
        res.add(r);
      }
      System.out.println("The query to ReservaAluguers was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return res;        
  }
  
  public List<Bicicleta> getBicicletasBaratas(){
    ResultSet result = null;
    List<Bicicleta> bic = new ArrayList<Bicicleta>();
    try {
      String queryString = "SELECT sb.idBicicleta, sb.designacao, sb.nova, sb.precoVenda, sb.imagem, c.nome, e.nome " +
                           "FROM (StockBicicletas sb LEFT JOIN Categorias c ON sb.idCategoria = c.idCategoria) LEFT JOIN " +
                           "Estados e ON sb.idEstado = e.idEstado " +
                           "WHERE e.idEstado = 4 " +
                           "ORDER BY sb.precoVenda ASC " +
                           "LIMIT 5";
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
        bc.setNomeEstado(result.getString(7));
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
  
  public void addCliente(Cliente clientBean) {
    ResultSet generatedKeys = null;
    String idCliente = "0";
    try {
      String queryString = "INSERT INTO Clientes (nomeCliente,password,nome,email,cartaoCredito) VALUES(?,?,?,?,?)";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
      ptmt.setString(1, clientBean.getNomeCliente());
      ptmt.setString(2, clientBean.getPassword());
      ptmt.setString(3, clientBean.getNome());
      ptmt.setString(4, clientBean.getEmail());
      ptmt.setString(5, clientBean.getCartaoCredito());
      ptmt.executeUpdate();
      generatedKeys = ptmt.getGeneratedKeys();
      if (generatedKeys.next()) idCliente = generatedKeys.getString(1);
      else throw new SQLException("Creating Cliente failed, no generated key obtained.");
      System.out.println("Client Added Successfully with the id " + idCliente + "!");
      clientBean.setIdCliente(idCliente);
    } 
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
  }
  
  public List<Reserva> getAllReservasPassadas(String idCliente) {
    ResultSet result = null;
    List<Reserva> lstRes = new ArrayList<Reserva>();
    try {
      String queryString = "SELECT ra.idReservaAluguer, ra.dataInicio, ra.dataFim, ra.preco, regA.idRegistoAluguer, c.nome, " +
      		                       "rc.quantidade " +
                           "FROM (((ReservaAluguers ra LEFT JOIN RegistoAluguers regA ON " +
                           "ra.idReservaAluguer = regA.idReservaAluguer) INNER JOIN Clientes cli ON ra.idCliente = cli.idCliente) " +
                           "INNER JOIN ReservaCategorias rc ON ra.idReservaAluguer = rc.idReservaAluguer) INNER JOIN " +
                           "Categorias c ON c.idCategoria = rc.idCategoria " +
                           "WHERE ra.idCliente = ? AND ra.dataInicio < CURRENT_DATE " +
                           "ORDER BY ra.dataInicio DESC ";
      /*SELECT ra.idReservaAluguer, ra.preco, ra.dataInicio, ra.dataFim, regA.idRegistoAluguer, rc.quantidade, c.nome, cli.nome
      FROM (((ReservaAluguers ra LEFT JOIN RegistoAluguers regA ON ra.idReservaAluguer = regA.idReservaAluguer) INNER JOIN Clientes cli ON ra.idCliente =
            cli.idCliente) INNER JOIN ReservaCategorias rc ON ra.idReservaAluguer = rc.idReservaAluguer) INNER JOIN Categorias c ON c.idCategoria = 
            rc.idCategoria
      ORDER BY ra.dataInicio DESC*/
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, idCliente);
      result = ptmt.executeQuery();
      while(result.next()){
        Reserva res = new Reserva();
        res.setIdReserva(result.getLong(1));
        res.setDataInicio(result.getDate(2));
        res.setDataFim(result.getDate(3));
        res.setPreco(result.getBigDecimal(4));
        res.setIdRegistoA(result.getLong(5));
        res.setNomeCategoria(result.getString(6));
        res.setQuantidade(result.getInt(7));
        lstRes.add(res);
      }
      System.out.println("The query to StockBicicletas was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return lstRes;
  }
  
  public List<Reserva> getAllReservasFuturas(String idCliente) {
    ResultSet result = null;
    List<Reserva> lstRes = new ArrayList<Reserva>();
    try {
      String queryString = "SELECT ra.idReservaAluguer, ra.dataInicio, ra.dataFim, ra.preco, regA.idRegistoAluguer, c.nome, " +
                                  "rc.quantidade " +
                           "FROM (((ReservaAluguers ra LEFT JOIN RegistoAluguers regA ON " +
                           "ra.idReservaAluguer = regA.idReservaAluguer) INNER JOIN Clientes cli ON ra.idCliente = cli.idCliente) " +
                           "INNER JOIN ReservaCategorias rc ON ra.idReservaAluguer = rc.idReservaAluguer) INNER JOIN " + 
                           "Categorias c ON c.idCategoria = rc.idCategoria " +
                           "WHERE ra.idCliente = ? AND ra.dataInicio > CURRENT_DATE + 1 " +
                           "ORDER BY ra.dataInicio ASC ";

      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, idCliente);
      result = ptmt.executeQuery();
      while(result.next()){
        Reserva res = new Reserva();
        res.setIdReserva(result.getLong(1));
        res.setDataInicio(result.getDate(2));
        res.setDataFim(result.getDate(3));
        res.setPreco(result.getBigDecimal(4));
        res.setIdRegistoA(result.getLong(5));
        res.setNomeCategoria(result.getString(6));
        res.setQuantidade(result.getInt(7));
        lstRes.add(res);
      }
      System.out.println("The query to StockBicicletas was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return lstRes;
  }
  
  public List<Reserva> getAllReservasAtuais(String idCliente) {
    ResultSet result = null;
    List<Reserva> lstRes = new ArrayList<Reserva>();
    try {
      String queryString = "SELECT ra.idReservaAluguer, ra.dataInicio, ra.dataFim, ra.preco, regA.idRegistoAluguer, c.nome, " +
                                  "rc.quantidade " +
                           "FROM (((ReservaAluguers ra LEFT JOIN RegistoAluguers regA ON " +
                           "ra.idReservaAluguer = regA.idReservaAluguer) INNER JOIN Clientes cli ON ra.idCliente = cli.idCliente) " +
                           "INNER JOIN ReservaCategorias rc ON ra.idReservaAluguer = rc.idReservaAluguer) INNER JOIN " + 
                           "Categorias c ON c.idCategoria = rc.idCategoria " +
                           "WHERE ra.idCliente = ? AND (ra.dataInicio = CURRENT_DATE OR ra.dataInicio = CURRENT_DATE + 1) " +
                           "ORDER BY ra.dataInicio ASC ";

      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, idCliente);
      result = ptmt.executeQuery();
      while(result.next()){
        Reserva res = new Reserva();
        res.setIdReserva(result.getLong(1));
        res.setDataInicio(result.getDate(2));
        res.setDataFim(result.getDate(3));
        res.setPreco(result.getBigDecimal(4));
        res.setIdRegistoA(result.getLong(5));
        res.setNomeCategoria(result.getString(6));
        res.setQuantidade(result.getInt(7));
        lstRes.add(res);
      }
      System.out.println("The query to StockBicicletas was executed successfully!");
    }
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return lstRes;
  }
  
  public int removeReservaCategoria(String idReserva){
    int success = 0;
    try {
      String queryString = "DELETE FROM ReservaCategorias WHERE idReservaAluguer = ?";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, idReserva);
      success = ptmt.executeUpdate();
      System.out.println("ReservaCategoria Deleted Successfully!");
    } 
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    
    return success;
  }
  
  public int removeReserva(String idReserva){
    int success = 0;
    try {
      String queryString = "DELETE FROM ReservaAluguers WHERE idReservaAluguer = ?";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, idReserva);
      success = ptmt.executeUpdate();
      System.out.println("Reserva Deleted Successfully!");
    } 
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    
    return success;
  }
}
