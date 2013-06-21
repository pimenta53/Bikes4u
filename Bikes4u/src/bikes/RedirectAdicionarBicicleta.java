package bikes;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikes.categorias.CategoriaBDAO;
import bikes.estado.EstadoDAO;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/RedirectAdicionarBicicleta" })
public class RedirectAdicionarBicicleta extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		CategoriaBDAO dao = new CategoriaBDAO();
		BicicletaDAO daoB = new BicicletaDAO();
		EstadoDAO daoE = new EstadoDAO();
		req.setAttribute("estados",daoE.findAll());
		req.setAttribute("biclas",daoB.findAll());
		req.setAttribute("categorias", dao.findAll());
		req.getRequestDispatcher("/listaBiclaStock.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}