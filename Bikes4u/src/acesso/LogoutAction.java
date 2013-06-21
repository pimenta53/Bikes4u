package acesso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/Logout"})
public class LogoutAction extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{

    //Verifica se existe o atributo cliente na sessao e caso existe, destroi-o
    //Verifica se existe o atributo funcionario na sessao e caso existe, destroi-o
    HttpSession session = req.getSession();
    if(session.getAttribute("cliente") != null) session.removeAttribute("cliente");
    if(session.getAttribute("funcionario") != null) session.removeAttribute("funcionario");

    req.getRequestDispatcher("/home.jsp").forward(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req,res);
  }

}
