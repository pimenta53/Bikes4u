package acesso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;

import util.PasswordHasher;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/RegistoCliente"})
public class RegistoClienteAction extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    
    Cliente cli = new Cliente();

    String nomeCliente = req.getParameter("nomeCliente");
    String email = req.getParameter("email");
    String pass = PasswordHasher.hash(req.getParameter("password"));
    String nome = req.getParameter("nome");
    String cartaoCreditoStr = req.getParameter("cartaoCredito");
    //if(!cartaoCreditoStr.isEmpty()) cartaoCredito = Long.parseLong(req.getParameter("cartaoCredito"));

    //Se os cinco campos estao preenchidos
    if (!nomeCliente.isEmpty() && !email.isEmpty() && !req.getParameter("password").isEmpty() && !nome.isEmpty() && 
          !cartaoCreditoStr.isEmpty()){
      
      AcessoDAO dao = new AcessoDAO();
      cli.setNomeCliente(nomeCliente);
      cli.setPassword(pass);
      cli.setNome(nome);
      cli.setEmail(email);
      cli.setCartaoCredito(cartaoCreditoStr);
      dao.addCliente(cli);
    }

    if (cli.getIdCliente() != null){
      HttpSession session = req.getSession();
      Cliente cliSession = new Cliente();
      cliSession.setIdCliente(cli.getIdCliente());
      cliSession.setNomeCliente(nomeCliente);
      cliSession.setEmail(email);
      cliSession.setNome(nome);
      cliSession.setCartaoCredito(cartaoCreditoStr);
      session.setAttribute("cliente", cliSession);
      res.sendRedirect("HomeRegistado.jsp");
    }
    else {
      req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
      res.sendRedirect("registoCliente.jsp");
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
  
}
