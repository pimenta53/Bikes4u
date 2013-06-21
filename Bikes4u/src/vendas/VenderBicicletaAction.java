package vendas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;
import beans.Venda;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/VenderBicicleta"})
public class VenderBicicletaAction extends HttpServlet {
  
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    
    if(req.getParameter("idBicicleta").isEmpty()){
      req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
      res.sendRedirect("BicicletasArmazem");
    }
    else{
      VendasDAO dao = new VendasDAO();
      Venda resV = new Venda();
      //get all of information of a sale
      HttpSession session = req.getSession();
      Login log = (Login) session.getAttribute("funcionario");
      resV.setFuncionario(log.getNome());
      resV.setIdBicicleta(Long.parseLong(req.getParameter("idBicicleta")));
      dao.getDadosVenda(resV);
      
      req.setAttribute("dadosVenda", resV);
      req.setAttribute("clientes", dao.getAllClientes());
      req.getRequestDispatcher("registoVenda.jsp").forward(req, res);
    }
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);    
  }
}
