package manutencao;

import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/RetirarManutencao" })
public class RetirarBicicletaManutencaoAction extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		int success = 0;

		if (!req.getParameter("idBicicleta").isEmpty()) {
			Long idBicicleta = Long.parseLong(req.getParameter("idBicicleta"));
			Long idManutencao = Long.parseLong(req.getParameter("idManutencao"));
			BigDecimal precoPosManutencao = new BigDecimal(req.getParameter("precoPosManutencao"));
			ManutencaoDAO dao = new ManutencaoDAO();
			success = dao.retirarBicicletaManutencao(idBicicleta, idManutencao, precoPosManutencao);
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
