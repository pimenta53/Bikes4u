package registarAluguer;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservas.ReservaAluguer;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/ListaReservas" })
public class ListaReservasAction extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		RegistarAluguerDAO dao = new RegistarAluguerDAO();
		List<ReservaAluguer> listaReservas = dao.getReservasAluguer();
		List<ReservaAluguer> listaReservasAConcluir = dao.getReservasAluguerAConcluir();
		List<ReservaAluguer> listaReservasPorConcluir = dao.getReservasAluguerPorConcluir();

		req.setAttribute("listaReservas", listaReservas);
		req.setAttribute("listaReservasAConcluir", listaReservasAConcluir);
		req.setAttribute("listaReservasPorConcluir", listaReservasPorConcluir);

		req.getRequestDispatcher("/listaReservas.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}

