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
@WebServlet(urlPatterns={"/RegistoMensalData"})
public class RegistoMensalData extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    int year = Calendar.getInstance().get(Calendar.YEAR);
    List<Integer> anos = new ArrayList<Integer>();
    for(int i = 2012; i <= year; i++) anos.add(i);
    req.setAttribute("ano", anos);
    
    List<Integer> meses = new ArrayList<Integer>();
    for(int i = 1; i <= 12; i++) meses.add(i);
    req.setAttribute("mes", meses);
    
    req.setAttribute("message", "Seleccione o ms e o ano de que pretende ver as contas");
    
    req.getRequestDispatcher("registoMensal.jsp").forward(req, res);
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
}
