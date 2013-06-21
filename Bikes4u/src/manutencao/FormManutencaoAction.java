package manutencao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vendas.VendasDAO;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/FormManutencao"})
public class FormManutencaoAction extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
      if(req.getParameter("idBicicleta").isEmpty()) {
        req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
        res.sendRedirect("BicicletasArmazem");
      }
      else{
        req.setAttribute("idBic", req.getParameter("idBicicleta"));
        req.getRequestDispatcher("registarManutencao.jsp").forward(req, res);
      }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
      doGet(req, res);
    }
}
