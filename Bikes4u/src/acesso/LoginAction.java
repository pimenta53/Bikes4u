package acesso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/Login"})
public class LoginAction extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{

    String email = req.getParameter("email");
    String pass = req.getParameter("pass");
    boolean correcto = false, cliente = false;;
    
    AcessoDAO dao = new AcessoDAO();
    Login l = new Login();
    l.setEmail(email);
    l.setPassword(pass);
    correcto = dao.checkCliente(l);
    if(correcto){
      Login log = new Login();
      log.setId(l.getId());
      log.setNomeLogin(l.getNomeLogin());
      log.setNome(l.getNome());
      log.setEmail(l.getEmail());
      log.setCartaoCredito(l.getCartaoCredito());
      HttpSession session = req.getSession();
      session.setAttribute("cliente", log);
      cliente = true;
    }
    else{
      correcto = dao.checkFuncionario(l);
      if(correcto){
        Login log = new Login();
        log.setId(l.getId());
        log.setNomeLogin(l.getNomeLogin());
        log.setNome(l.getNome());
        log.setEmail(l.getEmail());
        HttpSession session = req.getSession();
        session.setAttribute("funcionario", log);
      }
    }

    if (correcto) {
      if (cliente ) res.sendRedirect("Home");
      else res.sendRedirect("BicicletasArmazem");
    }
    else {
      req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
      res.sendRedirect("home.jsp");
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }

}
