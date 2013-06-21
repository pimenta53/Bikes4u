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
@WebServlet(urlPatterns = { "/RegistarManutencao" })
public class RegistarManutencaoAction extends HttpServlet {

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

			ManutencaoDAO dao = new ManutencaoDAO();

			manutencao.setDescricaoAvaria(descricaoAvaria);
			manutencao.setDataFim(new java.sql.Date(dataFim.getTime()));
			manutencao.setPrecoPosManutencao(precoPosManutencao);
			manutencao.setIdBicicleta(idBicicleta);
			manutencao.setIdFuncionario(idFuncionario);
			manutencao.setTerminado(0);
			success = dao.add(manutencao);
			if (success != 0) dao.adicionarBicicletaManutencao(idBicicleta);
		}
		else {
	      req.getSession().setAttribute("errorMessage", "A operação não foi bem sucedida!");
		  res.sendRedirect("BicicletasArmazem");
		  return;
		}

		if (success != 0) {
			res.sendRedirect("ListaManutencoes");
		} else {
			req.getRequestDispatcher("/RegistarManutencao").forward(req,res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}

}
