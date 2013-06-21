package manutencao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/RemoverManutencao" })
public class RemoverManutencaoAction extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		int success = 0;

		if (!req.getParameter("idManutencao").isEmpty()
				&& !req.getParameter("idBicicleta").isEmpty()
				&& !req.getParameter("terminado").isEmpty()) {

			Long idBicicleta = Long.parseLong(req.getParameter("idBicicleta"));
			Long idManutencao = Long.parseLong(req.getParameter("idManutencao"));
			int terminado = Integer.parseInt(req.getParameter("terminado"));

			ManutencaoDAO dao = new ManutencaoDAO();
			/* Se a manutencção já terminou */
			success = dao.deleteManutencao(idManutencao);
			if(terminado==0 && success!=0){
				success = dao.atualizarEstado(idBicicleta);
			}
		}

		if (success != 0) {
			res.sendRedirect("ListaManutencoes");
		} else {
			System.out.println("Erro");
			req.getRequestDispatcher("ListaManutencoes").forward(req, res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}

}
