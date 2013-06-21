package bikes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bikes.categorias.CategoriaBDAO;
import bikes.estado.EstadoDAO;

import util.*;

public class BicicletaDAO {

	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    private CategoriaBDAO cats = new CategoriaBDAO();
    private EstadoDAO estados = new EstadoDAO();
    
    public BicicletaDAO(){
    	
    }
    
	
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
}

    public void add(Bicicleta bicla) {
        try {
        	
        	
                String queryString = "INSERT INTO StockBicicletas(idBicicleta, designacao, nova, precovenda, idCategoria, idEstado, imagem)  VALUES(?,?,?,?,?,?,?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, bicla.getMatricula());
                ptmt.setString(2, bicla.getNome());
                ptmt.setInt(3, bicla.getNova());
                ptmt.setBigDecimal(4, bicla.getPreco());
                ptmt.setString(5, bicla.getCat().getId());
                ptmt.setString(6, bicla.getEstado().getId());
                ptmt.setString(7, bicla.getFoto());
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
            String queryString = "DELETE FROM StockBicicletas WHERE idBicicleta=?";
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

public void update(Bicicleta bicla) {

    try {
            String queryString = "UPDATE StockBicicletas SET designacao=?, nova=?, precovenda=?, idCategoria=?, idEstado=?, imagem=? WHERE idBicicleta=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, bicla.getNome());
            ptmt.setInt(2, bicla.getNova());
            ptmt.setBigDecimal(3, bicla.getPreco());
            ptmt.setString(4, bicla.getCat().getId());
            ptmt.setString(5, bicla.getEstado().getId());
            ptmt.setString(6, bicla.getFoto());
            ptmt.setString(7, bicla.getMatricula());
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


public int nBikesR(String id, Date inicio, Date fim) {
	   
	int total =0;
	try {
				String queryString = "SELECT count(sb.idBicicleta) as contador from StockBicicletas sb, ReservaCategorias rc, ReservaAluguers ra where  rc.idcategoria=? and sb.idcategoria=rc.idcategoria and ra.idreservaaluguer=rc.idreservaaluguer and ra.dataInicio <=  ?  AND ra.dataFim >=  ?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);            
                ptmt.setString(1, id);
                ptmt.setDate(2, inicio);
                ptmt.setDate(3, fim);
                resultSet = ptmt.executeQuery();
                resultSet.next();
                total = resultSet.getInt("contador");
                System.out.println("Estas s‹o as reservadas!!! "+total);
                
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
	return total;
	}
    
public int nBikesT(String id) {
	   
	int total =0;
	try {
				String queryString = "SELECT count(*) as contador from StockBicicletas WHERE idCategoria=? ";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);            
                ptmt.setString(1, id);
                resultSet = ptmt.executeQuery();
                resultSet.next();
                total = resultSet.getInt("contador");
                System.out.println(" saquei TOTAL DE BILAS PARA AQUI"+total);
                
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
	return total;
	}


public int nBikesVendidas(String idC) {
	   
	int total =0;
	try {
				String queryString = "SELECT count(*) as contador from StockBicicletas WHERE idCategoria=? AND idEstado=2 ";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);            
                ptmt.setString(1, idC);
                resultSet = ptmt.executeQuery();
                resultSet.next();
                total = resultSet.getInt("contador");
                System.out.println(" saquei endias "+total);
                
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
	return total;
	}



public List<Bicicleta> findAll() {
	   
	List<Bicicleta> result = new ArrayList<Bicicleta>();
	
	try {
                String queryString = "SELECT * FROM StockBicicletas";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                resultSet = ptmt.executeQuery();
                while (resultSet.next()) {
                  Bicicleta bic = new Bicicleta();
                  bic.setMatricula(resultSet.getString(1));
                  bic.setNome(resultSet.getString(2));
                  bic.setNova(resultSet.getInt(3));
                  bic.setPreco(resultSet.getBigDecimal(4));
                  bic.setCat(cats.findId(resultSet.getString(5)));
                  bic.setEstado(estados.findId(resultSet.getString(6)));
                  bic.setFoto(resultSet.getString(7));
                        System.out.println("id " + resultSet.getString("idBicicleta")
                                        + ", Name " + resultSet.getString("designacao") + ", Nova? "
                                        + resultSet.getBoolean("nova") + ", preco "
                                        + resultSet.getBigDecimal("precovenda")+ "idCat"+ resultSet.getString("idCategoria") + "idEstado "+ resultSet.getString("idEstado"));
               result.add(bic);
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
