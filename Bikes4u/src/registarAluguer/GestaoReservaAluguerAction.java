package registarAluguer;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Bicicleta;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/GestaoReservasAluguer"})
public class GestaoReservaAluguerAction extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		String idReservaAluguer = req.getParameter("idReservaAluguer");
		String idCategoria = req.getParameter("idCategoria");
		String nomeCategoria = req.getParameter("nomeCategoria");
		int quantidade = Integer.parseInt(req.getParameter("quantidade"));
		BigDecimal preco = new BigDecimal(req.getParameter("preco"));

		RegistarAluguerDAO dao = new RegistarAluguerDAO();
		List<Bicicleta> listaBicicletasReserva = dao.getBicicletasReserva(idCategoria, quantidade);
		req.setAttribute("listaBicicletasReserva", listaBicicletasReserva);
		req.setAttribute("idReservaAluguer", idReservaAluguer);
		req.setAttribute("idCategoria", idCategoria);
		req.setAttribute("nomeCategoria", nomeCategoria);
		req.setAttribute("quantidade", quantidade);
		req.setAttribute("preco", preco);

		req.getRequestDispatcher("/confirmarRegistoAluguer.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}
