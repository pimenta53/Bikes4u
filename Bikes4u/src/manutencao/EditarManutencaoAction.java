package manutencao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Login;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/EditarManutencao" })
public class EditarManutencaoAction extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		Manutencao manutencao = new Manutencao();
		int success = 0;

		HttpSession session = req.getSession();
		String idFuncionario = null;
		if (session.getAttribute("funcionario") != null) {
			idFuncionario = ((Login) session.getAttribute("funcionario"))
					.getId();
		}

		if (!req.getParameter("idManutencao").isEmpty()
				&& !req.getParameter("descricaoAvaria").isEmpty()
				&& !req.getParameter("dataFim").isEmpty()
				&& !req.getParameter("precoPosManutencao").isEmpty()
				&& !req.getParameter("idBicicleta").isEmpty()
				&& idFuncionario != null) {

			String idManutencao = req.getParameter("idManutencao");
			String descricaoAvaria = req.getParameter("descricaoAvaria");
			String formato = "yyyy-MM-dd";
			java.util.Date dataFim = null;
			try {
				dataFim = new SimpleDateFormat(formato).parse(req
						.getParameter("dataFim"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BigDecimal precoPosManutencao = new BigDecimal(
					req.getParameter("precoPosManutencao"));
			String idBicicleta = req.getParameter("idBicicleta");

			manutencao.setIdManutencao(idManutencao);
			manutencao.setDescricaoAvaria(descricaoAvaria);
			manutencao.setDataFim(new java.sql.Date(dataFim.getTime()));
			manutencao.setPrecoPosManutencao(precoPosManutencao);
			manutencao.setIdFuncionario(idFuncionario);
			manutencao.setIdBicicleta(idBicicleta);

			req.setAttribute("manutencao", manutencao);
			success = 1;
		}
		if (success != 0) {
			req.getRequestDispatcher("/editarManutencao.jsp").forward(req, res);
		} else {
			res.sendRedirect("ListaManutencoes");
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}
