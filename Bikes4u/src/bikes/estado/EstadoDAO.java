package bikes.estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionFactory;

import bikes.categorias.CategoriaEmprestimo;

public class EstadoDAO {

	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    
    public EstadoDAO(){
    	
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

 
    public EstadoB findId(String id){
    	
    	EstadoB estado = new EstadoB();
    	
    	try {
    	 String queryString = "SELECT * FROM Estados WHERE idEstado=?";
         connection = getConnection();
         ptmt = connection.prepareStatement(queryString);
         ptmt.setString(1,id);
         resultSet = ptmt.executeQuery();
         resultSet.next();
         estado.setId(resultSet.getString(1));
         estado.setEstado(resultSet.getString(2));
    	} catch (SQLException e) {
              e.printStackTrace();
      } finally {
              try {
                      if (resultSet != null)
                              resultSet.close();
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

    return estado;
    }
    
    public ArrayList<EstadoB> findAll() {
        
    	ArrayList<EstadoB> result = new ArrayList<EstadoB>();
    	try {
                    String queryString = "SELECT * FROM Estados";
                    connection = getConnection();
                    ptmt = connection.prepareStatement(queryString);
                    resultSet = ptmt.executeQuery();
                    while (resultSet.next()) {
                            System.out.println("idEstado " + resultSet.getString("idEstado")
                                            + ", Nome " + resultSet.getString("nome"));
                      EstadoB eb = new EstadoB();
                      eb.setId(resultSet.getString(1));
                      eb.setEstado(resultSet.getString(2));
                      result.add(eb);
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    try {
                            if (resultSet != null)
                                    resultSet.close();
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
    return result;
    }


    
}
