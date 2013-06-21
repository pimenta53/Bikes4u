package bikes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikes.categorias.CategoriaBDAO;

/**
 * Servlet implementation class DeletBikeAction
 */
@WebServlet("/DeletBikeAction")
public class DeletBikeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BicicletaDAO bikeDAO = new BicicletaDAO();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String page = "RedirectAdicionarBicicleta"; // por omissão
		String id = request.getParameter("id");
		bikeDAO.delete(id);
		System.out.println("trinta vou tentar apagar");
		response.sendRedirect(page); // crucial
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
