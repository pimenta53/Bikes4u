package financas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/RegistoDiario"})
public class RegistoDiario extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    FinancasDAO dao = new FinancasDAO();
    req.setAttribute("aluguers", dao.getAllAluguersDia());
    req.setAttribute("vendas", dao.getAllVendasDia());
    
    req.getRequestDispatcher("/registoDiario.jsp").forward(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }  
}
