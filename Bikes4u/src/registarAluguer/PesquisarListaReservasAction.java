package registarAluguer;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservas.ReservaAluguer;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/PesquisarListaReservas"})
public class PesquisarListaReservasAction extends HttpServlet{

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{

    String pesq = req.getParameter("textoPesquisa");
    RegistarAluguerDAO dao = new RegistarAluguerDAO();
    List<ReservaAluguer> listaReservas = dao.getReservasAluguerPesquisar(pesq);
    List<ReservaAluguer> listaReservasAConcluir = dao.getReservasAConcluirPesquisar(pesq);


    req.setAttribute("listaReservas", listaReservas);
    req.setAttribute("listaReservasAConcluir", listaReservasAConcluir);

    req.getRequestDispatcher("/listaReservas.jsp").forward(req, res);

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException{
    doGet(req, res);
  }
}

