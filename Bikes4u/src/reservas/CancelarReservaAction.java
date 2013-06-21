package reservas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acesso.AcessoDAO;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/CancelarReserva"})
public class CancelarReservaAction extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    String idReserva = req.getParameter("idReserva");
    
    AcessoDAO dao = new AcessoDAO();
    int sucesso = dao.removeReservaCategoria(idReserva);
    if(sucesso != 0) dao.removeReserva(idReserva);
    else req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
    
    res.sendRedirect("MinhasReservas");

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }

}
