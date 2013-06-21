package registarAluguer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/TerminarRegisto" })
public class TerminarRegistoAction extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		int success = 0;

		if (!req.getParameter("idReservaAluguer").isEmpty()) {

			String idReservaAluguer = req.getParameter("idReservaAluguer");

			RegistarAluguerDAO dao = new RegistarAluguerDAO();
			/* Se a manutencção já terminou */
			success = dao.terminarRegisto(idReservaAluguer);
		}

		if (success != 0) {
			res.sendRedirect("ListaReservas");
		} else {
			System.out.println("Erro");
			req.getRequestDispatcher("ListaReservas").forward(req, res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}
