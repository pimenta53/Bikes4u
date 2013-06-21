package acesso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/MinhasReservas"})
public class MinhasReservasAction extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{

    String idCliente;
    HttpSession session = req.getSession();
    if(session.getAttribute("cliente") != null) idCliente = ((Login) session.getAttribute("cliente")).getId();
    else {
      res.sendRedirect("error.jsp");
      return;
    }
    
    AcessoDAO dao = new AcessoDAO();
    req.setAttribute("reservasPassadas", dao.getAllReservasPassadas(idCliente));
    req.setAttribute("reservasFuturas", dao.getAllReservasFuturas(idCliente));
    req.setAttribute("reservasAtuais", dao.getAllReservasAtuais(idCliente));

    req.getRequestDispatcher("reservasProprias.jsp").forward(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req,res);
  }

}
