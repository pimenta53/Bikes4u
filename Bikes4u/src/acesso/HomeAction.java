package acesso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/Home"})
public class HomeAction extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    
    HttpSession session = req.getSession();
    String id = null;
    boolean cliente = false;
    if(session.getAttribute("cliente") != null) {
      id = ((Login) session.getAttribute("cliente")).getId();
      cliente = true;
    }

    AcessoDAO dao = new AcessoDAO();
    if (cliente){
      req.setAttribute("Reservas", dao.getReservas(id));
      req.setAttribute("BicicletasVenda", dao.getBicicletasBaratas());
    }

    req.getRequestDispatcher("/HomeRegistado.jsp").forward(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
   doGet(req, res); 
  }
}
