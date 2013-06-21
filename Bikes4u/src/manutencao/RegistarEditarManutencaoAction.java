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
@WebServlet(urlPatterns = { "/RegistarEditarManutencao" })
public class RegistarEditarManutencaoAction extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		Manutencao manutencao = new Manutencao();
		int success = 0;

		HttpSession session = req.getSession();
		String idFuncionario = null;
		if (session.getAttribute("funcionario") != null) {
			idFuncionario = ((Login) session.getAttribute("funcionario")).getId();
		}

		if (!req.getParameter("descricaoAvaria").isEmpty()
				&& !req.getParameter("dataFim").isEmpty()
				&& !req.getParameter("precoPosManutencao").isEmpty()
				&& !req.getParameter("idBicicleta").isEmpty()
				&& !req.getParameter("idManutencao").isEmpty()
				&& idFuncionario != null) {

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
			BigDecimal precoPosManutencao = new BigDecimal(req.getParameter("precoPosManutencao"));
			String idBicicleta = req.getParameter("idBicicleta");
			String idManutencao = req.getParameter("idManutencao");

			ManutencaoDAO dao = new ManutencaoDAO();

			manutencao.setDescricaoAvaria(descricaoAvaria);
			manutencao.setDataFim(new java.sql.Date(dataFim.getTime()));
			manutencao.setPrecoPosManutencao(precoPosManutencao);
			manutencao.setIdBicicleta(idBicicleta);
			manutencao.setIdFuncionario(idFuncionario);
			manutencao.setIdManutencao(idManutencao);
			success = dao.editar(manutencao);
			System.out.println("Success -> "+ success);
		}

		if (success != 0) {
			res.sendRedirect("ListaManutencoes");
		} else {
			req.getRequestDispatcher("/editarManutencao.jsp").forward(req,
					res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}
