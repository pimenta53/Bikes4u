package funcionarios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Funcionario;

import util.PasswordHasher;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/RegistoFuncionario"})
public class RegistoFuncionarioAction extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    
    Funcionario func = new Funcionario();
    int success = 0;

    String nomeFuncionario = req.getParameter("nomeFuncionario");
    String email = req.getParameter("email");
    String pass = PasswordHasher.hash(req.getParameter("password"));
    String nome = req.getParameter("nome");
    //if(!cartaoCreditoStr.isEmpty()) cartaoCredito = Long.parseLong(req.getParameter("cartaoCredito"));

    //Se os cinco campos estao preenchidos
    if (!nomeFuncionario.isEmpty() && !email.isEmpty() && !req.getParameter("password").isEmpty() && !nome.isEmpty()){
      
      RegistoFuncionarioDAO dao = new RegistoFuncionarioDAO();
      func.setNomeFuncionario(nomeFuncionario);
      func.setPassword(pass);
      func.setNome(nome);
      func.setEmail(email);
      success = dao.add(func);
    }

    if(success != 0) {
      res.sendRedirect("BicicletasArmazem");
    }
    else {
      req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
      res.sendRedirect("registoFuncionario.jsp");
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
  
}
