package vendas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/RegistoVenda"})
public class RegistoVendaAction extends HttpServlet {
  
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    
    boolean valid = false;
    String idBicicleta = req.getParameter("idBicicleta");
    String idCliente = null; 
    if(!req.getParameter("cliente").isEmpty()) idCliente = req.getParameter("cliente");

    HttpSession session = req.getSession();
    Login log = (Login) session.getAttribute("funcionario");
    String idFuncionario = log.getId();
    VendasDAO dao = new VendasDAO();
    valid = dao.addVenda(idBicicleta, idCliente, idFuncionario);
    if(valid){
      dao.setEstado(idBicicleta);
      res.sendRedirect("BicicletasArmazem");
    }
    else{
      req.getSession().setAttribute("errorMessage", "A opera�‹o n‹o foi bem sucedida!");
      res.sendRedirect("VenderBicicleta?idBicicleta=" + idBicicleta);
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
}
