package manutencao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/ListaManutencoes" })
public class ListaManutencoesAction extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		ManutencaoDAO dao = new ManutencaoDAO();
		List<Manutencoes> listaManutencoes = dao.getAllManutencoes();
		List<Manutencoes> listaManutencoesInicio = dao
				.getAllManutencoesIniciar();
		List<Manutencoes> listaManutencoesFim = dao
				.getAllManutencoesFim();
		List<Manutencoes> listaRegistoManutencoes = dao
				.getAllBicicletasManutencao();
		req.setAttribute("listaManutencoes", listaManutencoes);
		req.setAttribute("listaManutencoesFim", listaManutencoesFim);
		req.setAttribute("listaRegistoManutencoes", listaRegistoManutencoes);

		req.getRequestDispatcher("/listaManutencoes.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}
