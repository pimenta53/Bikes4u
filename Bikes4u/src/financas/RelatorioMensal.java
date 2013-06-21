package financas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/RegistoMensal"})
public class RelatorioMensal extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    Integer mes =  Integer.parseInt(req.getParameter("mes"));
    Integer ano = Integer.parseInt(req.getParameter("ano"));

    int year = Calendar.getInstance().get(Calendar.YEAR);
    List<Integer> anos = new ArrayList<Integer>();
    for(int i = 2012; i <= year; i++) anos.add(i);
    req.setAttribute("ano", anos);
    
    List<Integer> meses = new ArrayList<Integer>();
    for(int i = 1; i <= 12; i++) meses.add(i);
    req.setAttribute("mes", meses);

    FinancasDAO dao = new FinancasDAO();
    req.setAttribute("mesEscolhido", mes);
    req.setAttribute("anoEscolhido", ano);
    req.setAttribute("aluguersMes", dao.getAllAluguersMesAno(mes, ano));
    req.setAttribute("vendasMes", dao.getAllVendasMesAno(mes, ano));
    
    req.getRequestDispatcher("registoMensal.jsp").forward(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
}
