package funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.ConnectionFactory;


import beans.Funcionario;




public class RegistoFuncionarioDAO {
  Connection connection = null;
  PreparedStatement ptmt = null;

  public RegistoFuncionarioDAO() {

  }

  private Connection getConnection() throws SQLException {
    Connection conn;
    conn = ConnectionFactory.getInstance().getConnection();
    return conn;
  }

  public int add(Funcionario funcBean) {
    int success = 0;
    try {
      String queryString = "INSERT INTO Funcionarios (nomeFuncionario,password,nome,email) VALUES(?,?,?,?)";
      connection = getConnection();
      ptmt = (PreparedStatement) connection.prepareStatement(queryString);
      ptmt.setString(1, funcBean.getNomeFuncionario());
      ptmt.setString(2, funcBean.getPassword());
      ptmt.setString(3, funcBean.getNome());
      ptmt.setString(4, funcBean.getEmail());
      success = ptmt.executeUpdate();
      System.out.println("Funcionario " + funcBean.getNomeFuncionario() + " Added Successfully!");
    } 
    catch (SQLException e) {e.printStackTrace();} 
    finally {
      if (ptmt != null) try { ptmt.close(); } catch (SQLException logOrIgnore) {}
      if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
    }
    return success;
  }
}
