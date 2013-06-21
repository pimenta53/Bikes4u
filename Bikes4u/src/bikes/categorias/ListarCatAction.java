package bikes.categorias;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListarCatAction
 */
@WebServlet("/ListarCatAction")
public class ListarCatAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoriaBDAO catB = new CategoriaBDAO();
		List<CategoriaEmprestimo> cats = catB.findAll();
		request.setAttribute("categorias", cats);
		request.getRequestDispatcher("/listaCategorias.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
