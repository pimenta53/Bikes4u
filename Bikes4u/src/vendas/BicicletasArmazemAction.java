package vendas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/BicicletasArmazem" })
public class BicicletasArmazemAction extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    VendasDAO dao = new VendasDAO();
    req.setAttribute("bicicletasArma", dao.getAllBicicletasArmazem());
    req.getRequestDispatcher("bikesArmazem.jsp").forward(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
}
