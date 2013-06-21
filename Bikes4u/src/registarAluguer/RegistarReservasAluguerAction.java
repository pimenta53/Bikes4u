package registarAluguer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Bicicleta;
import beans.Login;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/RegistarReservasAluguer" })
public class RegistarReservasAluguerAction extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		RegistoAluguer registoAluguer = new RegistoAluguer();
		int success = 0;

		HttpSession session = req.getSession();
		String idFuncionario = null;
		if (session.getAttribute("funcionario") != null) {
			idFuncionario = ((Login) session.getAttribute("funcionario"))
					.getId();
		}
		if (!req.getParameter("idReservaAluguer").isEmpty()
				&& !req.getParameter("idCategoria").isEmpty()
				&& !req.getParameter("quantidade").isEmpty()
				&& idFuncionario != null) {

			List<Bicicleta> listaReservas = null;
			String idRegistoAluguer = null;
			RegistarAluguerDAO dao = new RegistarAluguerDAO();

			String idReservaAluguer = req.getParameter("idReservaAluguer");
			String idCategoria = req.getParameter("idCategoria");
			int quantidade = Integer.parseInt(req.getParameter("quantidade"));

			listaReservas = dao.getBicicletasReserva(idCategoria, quantidade);

			registoAluguer.setIdFuncionario(idFuncionario.toString());
			registoAluguer.setIdReservaAluguer(idReservaAluguer);

			idRegistoAluguer = dao.registarReservaAluguer(registoAluguer);
			for(Bicicleta b : listaReservas){
				success = dao.registarAluguerBicicletas(idRegistoAluguer, b.getIdBicicleta());
			}
		}

		if (success != 0) {
			res.sendRedirect("ListaReservas");
		} else {
			req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
			req.getRequestDispatcher("/ListaReservas").forward(req, res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}
