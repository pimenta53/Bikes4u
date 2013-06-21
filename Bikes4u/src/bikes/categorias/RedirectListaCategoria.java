package bikes.categorias;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/RedirectAdicionarCategoria" })
public class RedirectListaCategoria extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		CategoriaBDAO daoC = new CategoriaBDAO();
		req.setAttribute("categorias",daoC.findAll());
		req.getRequestDispatcher("/listaCategorias.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {
		doGet(req, res);
	}
}