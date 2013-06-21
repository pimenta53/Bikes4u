package listaBicicletas;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bicicleta;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/ListaBicicletas"})
public class ListaBicicletasAction extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    
    ListaBicicletasDAO dao = new ListaBicicletasDAO();
    List<Bicicleta> listaBiclas = dao.getAllBicicletas();
    req.setAttribute("listaBic", listaBiclas);
    
    req.getRequestDispatcher("/listaBicicletas.jsp").forward(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }

}
