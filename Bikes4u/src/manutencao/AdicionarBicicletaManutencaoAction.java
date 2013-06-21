package manutencao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/AdicionarManutencao" })
public class AdicionarBicicletaManutencaoAction extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		int success = 0;

		if (!req.getParameter("idBicicleta").isEmpty()) {
			String idBicicleta = req.getParameter("idBicicleta");
			ManutencaoDAO dao = new ManutencaoDAO();
			success = dao.adicionarBicicletaManutencao(idBicicleta);
		}

		if (success != 0) {
			res.sendRedirect("ListaManutencoes");
		} else {
			req.setAttribute("errorMessage", "A operação não foi bem sucedida!");
			req.getRequestDispatcher("/ListaManutencoes").forward(req, res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}
