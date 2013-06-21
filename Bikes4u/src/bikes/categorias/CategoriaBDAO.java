package bikes.categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionFactory;

public class CategoriaBDAO {

	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    
    public CategoriaBDAO(){
    	
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void add(CategoriaEmprestimo cat) {
        try {
        	
        	    String queryString = "INSERT INTO Categorias (nome, precoAluguer) VALUES (?,?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, cat.getTipo());
                System.out.println("nome"+cat.getTipo());
                ptmt.setBigDecimal(2, cat.getPreco());
                System.out.println("nova"+cat.getPreco());
                ptmt.executeUpdate();
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

    }

    
    public void delete(String id) {

        try {
                String queryString = "DELETE FROM Categorias WHERE idCategoria=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, id);
                ptmt.executeUpdate();
                System.out.println("Data deleted Successfully");
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

    
    public void update(CategoriaEmprestimo cat) {

        try {
        	String queryString = "UPDATE Categorias SET nome=?, precoAluguer=? WHERE idCategoria=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1,cat.getTipo());
                System.out.println("este novo nome "+ cat.getTipo());
                ptmt.setBigDecimal(2, cat.getPreco());
                ptmt.setString(3,cat.getId());
                ptmt.executeUpdate();
                System.out.println("Table Updated Successfully");
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                }

                catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();

                }
        }
}

 
    public CategoriaEmprestimo findId(String id){
    	
    	CategoriaEmprestimo cat = new CategoriaEmprestimo();
    	
    	try {
    	 String queryString = "SELECT * FROM Categorias WHERE idCategoria=? ";
         connection = getConnection();
         ptmt = connection.prepareStatement(queryString);
         ptmt.setString(1,id);
         resultSet = ptmt.executeQuery();
         resultSet.next();
         cat.setId(resultSet.getString(1));
         cat.setTipo(resultSet.getString(2));
         cat.setPreco(resultSet.getBigDecimal(3));  
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

    return cat;
    }
    
    public ArrayList<CategoriaEmprestimo> findAll() {
            
    	ArrayList<CategoriaEmprestimo> result = new ArrayList<CategoriaEmprestimo>();
    	try {
                    String queryString = "SELECT * FROM Categorias";
                    connection = getConnection();
                    ptmt = connection.prepareStatement(queryString);
                    resultSet = ptmt.executeQuery();
                    while (resultSet.next()) {
                            System.out.println("idCat " + resultSet.getString("idCategoria")
                                            + ", Nome " + resultSet.getString("nome") + ", preco "
                                            + resultSet.getDouble("precoAluguer"));
                      CategoriaEmprestimo cat = new CategoriaEmprestimo();
                      cat.setId(resultSet.getString(1));
                      cat.setTipo(resultSet.getString(2));
                      cat.setPreco(resultSet.getBigDecimal(3));  
                      result.add(cat);
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
